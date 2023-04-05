package com.nikpanfilov.episode.presentation

sealed class EpisodeState {

	object Initial : EpisodeState()

	data class Content(val sendState: EpisodeSendState) : EpisodeState()
}

sealed class EpisodeSendState {

	object Input : EpisodeSendState()

	object Loading : EpisodeSendState()

	object Success : EpisodeSendState()

	data class Error(val errorCode: Int) : EpisodeSendState()
}