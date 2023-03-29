package com.nikpanfilov.splash.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.token.domain.usecase.LoadTokenUseCase
import com.nikpanfilov.core.network.token.domain.usecase.RefreshTokensUseCase
import com.nikpanfilov.core.network.token.domain.usecase.SaveTokenUseCase
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.splash.domain.usecase.CheckFirstStartUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashViewModel(
	private val router: SplashRouter,
	loadTokenUseCase: LoadTokenUseCase,
	private val saveTokenUseCase: SaveTokenUseCase,
	private val refreshTokensUseCase: RefreshTokensUseCase,
	private val checkFirstStartUseCase: CheckFirstStartUseCase
) : ViewModel() {

	private val _stateFlow = MutableStateFlow<SplashState>(SplashState.Initial)
	val stateFlow: Flow<SplashState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? SplashState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = SplashSendState.Error(code))
	}

	init {
		if (checkFirstStartUseCase()) {
			navigateToSignUp()
		} else {
			val tokens = loadTokenUseCase()
			if (tokens.accessToken != "") {
				viewModelScope.launch(sendErrorHandler) {
					_stateFlow.value = SplashState.Content(SplashSendState.Loading)

					val newTokens = refreshTokensUseCase()
					saveTokenUseCase(newTokens)

					_stateFlow.value = SplashState.Content(SplashSendState.Success)
				}
			} else {
				navigateToSignIn()
			}
		}
	}

	private fun navigateToSignUp() {
		router.navigateToSignUpScreen()
	}

	fun navigateToSignIn() {
		router.navigateToSignInScreen()
	}

	fun navigateToMain() {
		router.navigateToMainScreen()
	}
}
