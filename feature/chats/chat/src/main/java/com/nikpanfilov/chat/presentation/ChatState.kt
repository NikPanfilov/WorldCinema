package com.nikpanfilov.chat.presentation

sealed class ChatState {

	object Initial : ChatState()

	data class Content(val sendState: ChatSendState) : ChatState()
}

sealed class ChatSendState {

	object Input : ChatSendState()

	object Loading : ChatSendState()

	object Success : ChatSendState()

	data class Error(val errorCode: Int) : ChatSendState()
}