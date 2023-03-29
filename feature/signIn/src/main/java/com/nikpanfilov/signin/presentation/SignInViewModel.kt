package com.nikpanfilov.signin.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.token.domain.usecase.SaveTokenUseCase
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.signin.R
import com.nikpanfilov.signin.domain.entity.AuthCredential
import com.nikpanfilov.signin.domain.usecase.SignInUseCase
import com.nikpanfilov.validators.ValidateEmailUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignInViewModel(
	private val router: SignInRouter,
	private val signInUseCase: SignInUseCase,
	private val validateEmailUseCase: ValidateEmailUseCase,
	private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

	val emailMutableFlow = MutableStateFlow<String?>(null)
	val passwordMutableFlow = MutableStateFlow<String?>(null)

	private val _stateFlow = MutableStateFlow<SignInState>(SignInState.Initial)
	val stateFlow: Flow<SignInState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? SignInState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = SignInSendState.Error(code))
	}

	init {
		_stateFlow.value = SignInState.Content(SignInSendState.Input)
	}

	fun signIn() {
		val contentState = _stateFlow.value as? SignInState.Content ?: return
		if (contentState.sendState is SignInSendState.Loading) return

		if (emailMutableFlow.value == null || passwordMutableFlow.value == null) return

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = contentState.copy(sendState = SignInSendState.Loading)
			val tokens = signInUseCase(
				AuthCredential(
					emailMutableFlow.value ?: error("Email can't be null!"),
					passwordMutableFlow.value ?: error("Password can't be null!")
				)
			)
			saveTokenUseCase(tokens)

			_stateFlow.value = contentState.copy(sendState = SignInSendState.Success)
		}
	}

	fun navigateToMain() {
		router.navigateToMain()
	}

	fun navigateToSignUp() {
		router.navigateToSignUp()
	}

	fun getEmailError() = emailMutableFlow.value?.let { validateEmailUseCase(it) }

	fun getPasswordError() = if (passwordMutableFlow.value?.isBlank() == true) {
		R.string.password_empty
	} else {
		null
	}
}