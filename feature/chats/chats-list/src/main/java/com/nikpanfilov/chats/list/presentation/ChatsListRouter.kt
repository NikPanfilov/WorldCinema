package com.nikpanfilov.chats.list.presentation

interface ChatsListRouter {

	fun navigateToChat(chatId: String, chatName: String)
	fun navigateBack()
}