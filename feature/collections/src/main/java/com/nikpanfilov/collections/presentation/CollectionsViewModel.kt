package com.nikpanfilov.collections.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.usecase.GetCollectionsUseCase
import com.nikpanfilov.shared.collections.iconsId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CollectionsViewModel(
	private val router: CollectionsRouter,
	private val getCollectionsUseCase: GetCollectionsUseCase,
) : ViewModel() {

	val collectionsMutableFlow: MutableStateFlow<List<Collection>?> = MutableStateFlow(null)

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

	fun getCollections() {
		viewModelScope.launch(sendErrorHandler) {
			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Loading)
			collectionsMutableFlow.value = getCollectionsUseCase()
			_stateFlow.value = CollectionsState.Content(CollectionsSendState.Success)
		}
	}

	fun createCollection() {
		router.navigateToCollectionCreate(Collection(collectionId = "", name = "", iconRes = iconsId[0]))
	}

	fun navigateToMovieCollection(collection: Collection) {
		router.navigateToMovies(collection)
	}
}