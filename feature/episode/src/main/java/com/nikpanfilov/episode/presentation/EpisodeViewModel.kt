package com.nikpanfilov.episode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieInfoHolder
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.episode.domain.entity.EpisodeTime
import com.nikpanfilov.episode.domain.entity.toEpisode
import com.nikpanfilov.episode.domain.entity.toMovieInfo
import com.nikpanfilov.episode.domain.usecase.GetTimeUseCase
import com.nikpanfilov.episode.domain.usecase.SetTimeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
	private val router: EpisodeRouter,
	private val getTimeUseCase: GetTimeUseCase,
	private val setTimeUseCase: SetTimeUseCase,
	episodeHolder: EpisodeHolder,
	movieInfoHolder: MovieInfoHolder
) : ViewModel() {

	val episode = episodeHolder.toEpisode()
	val movieInfo = movieInfoHolder.toMovieInfo()
	var episodeTime: Long = 0

	private val _stateFlow = MutableStateFlow<EpisodeState>(EpisodeState.Initial)
	val stateFlow: Flow<EpisodeState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? EpisodeState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = EpisodeSendState.Error(code))
	}

	init {
		_stateFlow.value = EpisodeState.Content(EpisodeSendState.Input)

		viewModelScope.launch(sendErrorHandler) {
			episodeTime = getTimeUseCase(episode.episodeId).timeInSeconds ?: 0
		}
	}

	fun addToCollection() {

	}

	fun addToFavourite() {

	}

	fun navigateToComments(viewedTime: Long) {
		saveViewTime(viewedTime)
	}

	fun navigateToMovie(viewedTime: Long) {
		saveViewTime(viewedTime)
	}

	private fun saveViewTime(time: Long) {
		viewModelScope.launch(sendErrorHandler) {
			setTimeUseCase(episode.episodeId, EpisodeTime(time))
		}
	}
}