package com.nikpanfilov.collections.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.collections.FAVOURITE
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm
import com.nikpanfilov.shared.collections.domain.entity.MovieValue
import com.nikpanfilov.shared.collections.domain.usecase.AddMovieToCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.CreateCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.DeleteCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetCollectionsUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditCollectionViewModel(
	val collection: Collection,
	private val router: EditCollectionRouter,
	private val getCollectionsUseCase: GetCollectionsUseCase,
	private val createCollectionUseCase: CreateCollectionUseCase,
	private val deleteCollectionUseCase: DeleteCollectionUseCase,
	private val getMoviesUseCase: GetMoviesUseCase,
	private val addMovieToCollectionUseCase: AddMovieToCollectionUseCase
) : ViewModel() {

	private val collectionsMutableFlow: MutableStateFlow<List<Collection>?> = MutableStateFlow(null)

	private val _stateFlow = MutableStateFlow<CollectionsState>(CollectionsState.Initial)
	val stateFlow: Flow<CollectionsState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? CollectionsState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = CollectionsSendState.Error(code))
	}

	init {
		_stateFlow.value = CollectionsState.Content(CollectionsSendState.Input)
		getCollections()
	}

	private fun getCollections() {
		viewModelScope.launch(sendErrorHandler) {
			collectionsMutableFlow.value = getCollectionsUseCase()
		}
	}

	fun navigateToIconChoose(collection: Collection) {
		router.navigateToIconChoose(collection)
	}

	fun createCollection(name: String, iconId: Int) {
		if (isCollectionExist(name))
			return

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Loading)

			createCollectionUseCase(CollectionForm("$iconId//$name"))
			navigateToCollections()

			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Success)
		}
	}

	fun saveCollection(name: String, iconId: Int) {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Loading)

			val movies = getMoviesUseCase(collection.collectionId)
			deleteCollection(collection.collectionId)
			val collection = createCollectionUseCase(CollectionForm("$iconId//$name"))
			if (movies.isNotEmpty()) {
				movies.forEach {
					addMovieToCollectionUseCase(collection.collectionId, MovieValue(movieId = it.movieId))
				}
			}

			navigateToCollections()

			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Success)
		}
	}

	fun deleteCollection(id: String) {
		if (id == (collectionsMutableFlow.value?.find { it.name == FAVOURITE }?.collectionId ?: ""))
			return

		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Loading)

			deleteCollectionUseCase(id)
			navigateToCollections()

			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Success)
		}
	}

	fun navigateBack() {
		router.navigateBack(collection)
	}

	private fun navigateToCollections() {
		router.navigateToCollections()
	}

	private fun isCollectionExist(name: String) = collectionsMutableFlow.value?.find { it.name == name } != null
}