package com.nikpanfilov.episode.presentation

interface EpisodeRouter {

	fun navigateBack()
	fun navigateToChat(chatId: String, chatName: String)
}