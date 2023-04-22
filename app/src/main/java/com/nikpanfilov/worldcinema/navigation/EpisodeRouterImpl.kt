package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.chat.ChatDestination
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.episode.presentation.EpisodeRouter

class EpisodeRouterImpl(private val router: GlobalRouter) : EpisodeRouter {

	override fun navigateBack() {
		router.exit()
	}

	override fun navigateToChat(chatId: String, chatName: String) {
		router.open(ChatDestination(chatId, chatName))
	}
}