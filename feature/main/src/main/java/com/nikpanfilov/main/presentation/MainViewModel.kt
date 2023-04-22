package com.nikpanfilov.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.main.domain.entity.Cover
import com.nikpanfilov.main.domain.entity.EpisodeView
import com.nikpanfilov.main.domain.usecase.GetCoverUseCase
import com.nikpanfilov.main.domain.usecase.GetHistoryUseCase
import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.nikpanfilov.shared.movie.domain.entity.toMovieHolder
import com.nikpanfilov.shared.movie.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
	private val router: MainRouter,
	private val getMoviesUseCase: GetMoviesUseCase,
	private val getCoverUseCase: GetCoverUseCase,
	private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

	companion object {

		const val IN_TREND = "inTrend"
		const val NEW = "new"
		const val FOR_ME = "forMe"
		const val LAST_VIEW = "lastView"
	}

	private val _stateFlow = MutableStateFlow<MainState>(MainState.Initial)
	val stateFlow: Flow<MainState>
		get() = _stateFlow.asStateFlow()

	val trendMoviesMutableFlow: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
	val newMoviesMutableFlow: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
	val forYouMoviesMutableFlow: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())

	val lastViewMutableFlow: MutableStateFlow<List<Movie>> = MutableStateFlow(listOf())
	private val lastEpisodesMutableFlow: MutableStateFlow<List<EpisodeView>> = MutableStateFlow(listOf())

	val coverMutableFlow: MutableStateFlow<Cover?> = MutableStateFlow(null)

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? MainState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = MainSendState.Error(code))
	}

	init {
		_stateFlow.value = MainState.Content(MainSendState.Input)

		trendMoviesMutableFlow.getMovies(IN_TREND)
		newMoviesMutableFlow.getMovies(NEW)
		forYouMoviesMutableFlow.getMovies(FOR_ME)
		lastViewMutableFlow.getMovies(LAST_VIEW)

		lastEpisodesMutableFlow.getLastEpisode()

		getCover()
	}

	private fun MutableStateFlow<List<EpisodeView>>.getLastEpisode() {
		viewModelScope.launch(sendErrorHandler) {
			this@getLastEpisode.value = getHistoryUseCase()
		}
	}

	fun navigateToMovie(movie: Movie) {
		router.navigateToMovie(movie.toMovieHolder())
	}

	fun navigateToLastViewEpisode() {
		if (lastViewMutableFlow.value.isNotEmpty() && lastEpisodesMutableFlow.value.isNotEmpty())
			router.navigateToEpisode(buildEpisodeHolder(lastEpisodesMutableFlow.value[0], lastViewMutableFlow.value[0]))
	}

	private fun buildEpisodeHolder(episode: EpisodeView, movie: Movie) = EpisodeHolder(
		episodeId = episode.episodeId,
		name = episode.episodeName,
		description = movie.description,
		filePath = episode.filePath,
		movieId = movie.movieId,
		movieName = movie.name,
		moviePoster = movie.poster,
		movieYears = "",
		chatId = movie.chatInfo.chatId,
		chatName = movie.chatInfo.chatName
	)

	private fun MutableStateFlow<List<Movie>>.getMovies(filter: String) {
		viewModelScope.launch(sendErrorHandler) {
			this@getMovies.value = getMoviesUseCase(filter)
		}
	}

	private fun getCover() {
		viewModelScope.launch(sendErrorHandler) {
			coverMutableFlow.value = getCoverUseCase()
		}
	}
}