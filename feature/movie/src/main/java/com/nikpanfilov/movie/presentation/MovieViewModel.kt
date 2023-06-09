package com.nikpanfilov.movie.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.movie.domain.entity.Episode
import com.nikpanfilov.movie.domain.entity.Movie
import com.nikpanfilov.movie.domain.entity.toMovie
import com.nikpanfilov.movie.domain.usecase.GetEpisodesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val router: MovieRouter, private val getEpisodesUseCase: GetEpisodesUseCase, private val movieHolder: MovieHolder) :
	ViewModel() {

	val movie = movieHolder.toMovie()
	val episodesFlow: MutableStateFlow<List<Episode>?> = MutableStateFlow(null)

	private val _stateFlow = MutableStateFlow<MovieState>(MovieState.Initial)
	val stateFlow: Flow<MovieState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? MovieState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = MovieSendState.Error(code))
	}

	init {
		_stateFlow.value = MovieState.Content(MovieSendState.Input)
		episodesFlow.getEpisodes()
	}

	private fun MutableStateFlow<List<Episode>?>.getEpisodes() {
		viewModelScope.launch(sendErrorHandler) {
			this@getEpisodes.value = getEpisodesUseCase(movie.movieId)
		}
	}

	fun navigateToChat() {
		router.navigateToChat(movie.chatInfo.chatId, movie.chatInfo.chatName)
	}

	fun navigateToEpisode(episode: Episode) {
		router.navigateToEpisode(episode.toEpisodeHolder(movie))
	}

	fun navigateBack() {
		router.navigateBack()
	}

	private fun Episode.toEpisodeHolder(movie: Movie) = EpisodeHolder(
		episodeId = episodeId,
		name = name,
		description = description,
		filePath = filePath,
		movieId = movie.movieId,
		movieName = movie.name,
		moviePoster = movie.poster,
		movieYears = getYears(episodesFlow.value!!),
		chatId = movie.chatInfo.chatId,
		chatName = movie.chatInfo.chatName
	)

	private fun getYears(episodes: List<Episode>): String {
		val years = episodes.map { it.year }.sorted()
		return if (years.size == 1) {
			years[0]
		} else {
			years.first() + "-" + years.last()
		}
	}
}