package com.nikpanfilov.splash.presentation

sealed class SplashState {

	object Initial : SplashState()

	data class Content(val sendState: SplashSendState) : SplashState()
}

sealed class SplashSendState {

	object Input : SplashSendState()

	object Loading : SplashSendState()

	object Success : SplashSendState()

	data class Error(val errorCode: Int) : SplashSendState()
}