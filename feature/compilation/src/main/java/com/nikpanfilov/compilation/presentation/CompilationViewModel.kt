package com.nikpanfilov.compilation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.compilation.domain.usecase.DislikeMovieUseCase
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.collections.domain.entity.MovieValue
import com.nikpanfilov.shared.collections.domain.usecase.AddMovieToCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetFavouriteCollectionIdUseCase
import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.nikpanfilov.shared.movie.domain.entity.toMovieHolder
import com.nikpanfilov.shared.movie.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompilationViewModel(
	private val router: CompilationRouter,
	private val getMoviesUseCase: GetMoviesUseCase,
	private val dislikeMovieUseCase: DislikeMovieUseCase,
	private val addMovieToCollectionUseCase: AddMovieToCollectionUseCase,
	private val getFavouriteCollectionIdUseCase: GetFavouriteCollectionIdUseCase
) : ViewModel() {

	companion object {

		const val COMPILATION = "compilation"
	}

	private val _stateFlow = MutableStateFlow<CompilationState>(CompilationState.Initial)
	val stateFlow: Flow<CompilationState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? CompilationState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = CompilationSendState.Error(code))
	}

	val moviesFlow = MutableStateFlow<List<Movie>>(listOf())

	init {
		_stateFlow.value = CompilationState.Content(CompilationSendState.Input)
		getMovies()
	}

	private fun getMovies() {
		viewModelScope.launch(sendErrorHandler) {
			moviesFlow.value = getMoviesUseCase(COMPILATION)
		}
	}

	fun dislike(movieId: String) {
		viewModelScope.launch(sendErrorHandler) {
			dislikeMovieUseCase(movieId)
		}
	}

	fun toFavourite(movieId: String) {
		viewModelScope.launch(sendErrorHandler) {
			addMovieToCollectionUseCase(getFavouriteCollectionIdUseCase(), MovieValue(movieId))
		}
	}

	fun navigateToMovie(movie: Movie) {
		router.navigateToMovie(movie.toMovieHolder())
	}
}