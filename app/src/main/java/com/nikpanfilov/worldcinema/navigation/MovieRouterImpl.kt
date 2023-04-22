package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.chat.ChatDestination
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.episode.EpisodeDestination
import com.nikpanfilov.movie.presentation.MovieRouter

class MovieRouterImpl(
	private val router: GlobalRouter
) : MovieRouter {

	override fun navigateToEpisode(episode: EpisodeHolder) {
		router.open(EpisodeDestination(episode))
	}

	override fun navigateToChat(chatId: String, chatName: String) {
		router.open(ChatDestination(chatId, chatName))
	}

	override fun navigateBack() {
		router.exit()
	}
}