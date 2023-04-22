package com.nikpanfilov.movie.presentation

import com.nikpanfilov.core.navigation.holders.EpisodeHolder

interface MovieRouter {

	fun navigateToEpisode(episode: EpisodeHolder)
	fun navigateToChat(chatId: String, chatName: String)
	fun navigateBack()
}