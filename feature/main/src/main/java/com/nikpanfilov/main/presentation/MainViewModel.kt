package com.nikpanfilov.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.main.domain.entity.Cover
import com.nikpanfilov.main.domain.entity.Movie
import com.nikpanfilov.main.domain.usecase.GetCoverUseCase
import com.nikpanfilov.main.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val router: MainRouter, private val getMoviesUseCase: GetMoviesUseCase, private val getCoverUseCase: GetCoverUseCase) :
	ViewModel() {

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

		getCover()
	}

	fun navigateToMovie(movie: String) {
		router.navigateToMovie(movie)
	}

	fun navigateToLastViewEpisode() {
		router.navigateToEpisode(lastViewMutableFlow.value[0].movieId)
	}

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