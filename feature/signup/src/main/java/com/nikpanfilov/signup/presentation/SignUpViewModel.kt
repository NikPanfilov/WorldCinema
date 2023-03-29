package com.nikpanfilov.signup.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.token.domain.usecase.SaveTokenUseCase
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.signup.R
import com.nikpanfilov.signup.domain.entity.RegistrationBody
import com.nikpanfilov.signup.domain.usecase.SignUpUseCase
import com.nikpanfilov.validators.ValidateEmailUseCase
import com.nikpanfilov.validators.ValidateFirstNameUseCase
import com.nikpanfilov.validators.ValidateLastNameUseCase
import com.nikpanfilov.validators.ValidatePasswordUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
	private val router: SignUpRouter,
	private val signUpUseCase: SignUpUseCase,
	private val saveTokenUseCase: SaveTokenUseCase,
	private val validateFirstNameUseCase: ValidateFirstNameUseCase,
	private val validateLastNameUseCase: ValidateLastNameUseCase,
	private val validateEmailUseCase: ValidateEmailUseCase,
	private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

	val firstNameFlow = MutableStateFlow<String?>(null)
	val lastNameFlow = MutableStateFlow<String?>(null)
	val emailFlow = MutableStateFlow<String?>(null)
	val passwordFlow = MutableStateFlow<String?>(null)
	val passwordRepeatFlow = MutableStateFlow<String?>(null)

	private val _stateFlow = MutableStateFlow<SignUpState>(SignUpState.Initial)
	val stateFlow: Flow<SignUpState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? SignUpState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = SignUpSendState.Error(code))
	}

	init {
		_stateFlow.value = SignUpState.Content(SignUpSendState.Input)
	}

	fun signUp() {
		val contentState = _stateFlow.value as? SignUpState.Content ?: return
		if (contentState.sendState is SignUpSendState.Loading) return

		if (!isSignUpDataValid()) return

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = contentState.copy(sendState = SignUpSendState.Loading)
			val tokens = signUpUseCase(
				RegistrationBody(
					firstName = firstNameFlow.value ?: error("Firstname can't be null"),
					lastName = lastNameFlow.value ?: error("Lastname can't be null"),
					email = emailFlow.value ?: error("Email can't be null"),
					password = passwordFlow.value ?: error("Password can't be null")
				)
			)
			saveTokenUseCase(tokens)
			_stateFlow.value = contentState.copy(sendState = SignUpSendState.Success)
		}
	}

	//TODO(Заменить это на что-нибудь вменяемое)
	private fun isSignUpDataValid() =
		getFirstNameError() == null &&
			getLastNameError() == null &&
			getEmailError() == null &&
			getPasswordError() == null &&
			getPasswordRepeatError() == null &&
			firstNameFlow.value != null &&
			lastNameFlow.value != null &&
			emailFlow.value != null &&
			passwordFlow.value != null

	fun navigateToMain() {
		router.navigateToMain()
	}

	fun navigateToSignIn() {
		router.navigateToSignIn()
	}

	fun getFirstNameError() = firstNameFlow.value?.let { validateFirstNameUseCase(it) }

	fun getLastNameError() = lastNameFlow.value?.let { validateLastNameUseCase(it) }

	fun getEmailError() = emailFlow.value?.let { validateEmailUseCase(it) }

	fun getPasswordError() = passwordFlow.value?.let { validatePasswordUseCase(it) }

	fun getPasswordRepeatError() = if (passwordFlow.value != passwordRepeatFlow.value) {
		R.string.different_passwords_error
	} else {
		null
	}
}