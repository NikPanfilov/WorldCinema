package com.nikpanfilov.profile.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.profile.domain.usecase.LogOutUseCase
import com.nikpanfilov.profile.domain.usecase.SetAvatarUseCase
import com.nikpanfilov.shared.profile.domain.entity.UserData
import com.nikpanfilov.shared.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ProfileViewModel(
	private val router: ProfileRouter,
	private val getProfileUseCase: GetProfileUseCase,
	private val setAvatarUseCase: SetAvatarUseCase,
	private val logOutUseCase: LogOutUseCase
) : ViewModel() {

	companion object {

		const val PHOTO_QUALITY = 100
	}

	val userDataFlow: MutableStateFlow<UserData?> = MutableStateFlow(null)

	private val _stateFlow = MutableStateFlow<ProfileState>(ProfileState.Initial)
	val stateFlow: Flow<ProfileState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? ProfileState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = ProfileSendState.Error(code))
	}

	init {
		getUserData()
	}

	fun navigateToChats() {
		router.navigateToChats()
	}

	private fun getUserData() {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = ProfileState.Content(ProfileSendState.Loading)
			userDataFlow.value = getProfileUseCase()
			_stateFlow.value = ProfileState.Content(ProfileSendState.Success)
		}
	}

	fun logout() {
		logOutUseCase()
		router.navigateToSignIn()
	}

	fun setAvatar(imageUrl: String) {
		val url = URL(imageUrl)
		val connection = url.openConnection() as HttpURLConnection
		url.openConnection().connect()
		val inputStream: InputStream = BufferedInputStream(connection.inputStream)
		val bitmap = BitmapFactory.decodeStream(inputStream)

		saveAvatar(bitmap)
	}

	fun saveAvatar(bitmap: Bitmap) {
		val byteArrayOutputStream = ByteArrayOutputStream()
		bitmap.compress(Bitmap.CompressFormat.PNG, PHOTO_QUALITY, byteArrayOutputStream)
		val photo = byteArrayOutputStream.toByteArray()

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = ProfileState.Content(ProfileSendState.Loading)
			setAvatarUseCase(photo)
			_stateFlow.value = ProfileState.Content(ProfileSendState.Success)
		}
	}
}