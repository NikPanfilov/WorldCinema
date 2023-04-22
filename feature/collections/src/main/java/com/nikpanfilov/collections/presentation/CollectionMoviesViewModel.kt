package com.nikpanfilov.collections.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.Movie
import com.nikpanfilov.shared.collections.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CollectionMoviesViewModel(
	val collection: Collection,
	private val router: CollectionMoviesRouter,
	private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

	val moviesMutableFlow: MutableStateFlow<List<Movie>?> = MutableStateFlow(null)

	private val _stateFlow = MutableStateFlow<CollectionsState>(CollectionsState.Initial)
	val stateFlow: Flow<CollectionsState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? CollectionsState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = CollectionsSendState.Error(code))
	}

	init {
		_stateFlow.value = CollectionsState.Content(CollectionsSendState.Input)
		getMovies()
	}

	private fun getMovies() {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Loading)

			moviesMutableFlow.value = getMoviesUseCase(collection.collectionId)

			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Success)
		}
	}

	fun navigateToCollectionEdit() {
		router.navigateToCollectionEdit(collection)
	}

	fun navigateBack() {
		router.navigateBack()
	}
}