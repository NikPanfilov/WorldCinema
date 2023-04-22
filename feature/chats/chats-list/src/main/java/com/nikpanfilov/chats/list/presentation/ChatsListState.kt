package com.nikpanfilov.chats.list.presentation

sealed class ChatsListState {

	object Initial : ChatsListState()

	data class Content(val sendState: ChatsListSendState) : ChatsListState()
}

sealed class ChatsListSendState {

	object Input : ChatsListSendState()

	object Loading : ChatsListSendState()

	object Success : ChatsListSendState()

	data class Error(val errorCode: Int) : ChatsListSendState()
}