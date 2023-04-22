package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.chat.ChatDestination
import com.nikpanfilov.chat.presentation.ChatRouter
import com.nikpanfilov.chats.list.presentation.ChatsListRouter
import com.nikpanfilov.core.navigation.GlobalRouter

class ChatRouterImpl(private val router: GlobalRouter) : ChatRouter {

	override fun navigateBack() {
		router.exit()
	}
}

class ChatsListRouterImpl(private val router: GlobalRouter) : ChatsListRouter {

	override fun navigateToChat(chatId: String, chatName: String) {
		router.open(ChatDestination(chatId, chatName))
	}

	override fun navigateBack() {
		router.exit()
	}
}