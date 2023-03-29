package com.nikpanfilov.main.presentation

sealed class MainState {

	object Initial : MainState()

	data class Content(val sendState: MainSendState) : MainState()
}

sealed class MainSendState {

	object Input : MainSendState()

	object Loading : MainSendState()

	object Success : MainSendState()

	data class Error(val errorCode: Int) : MainSendState()
}