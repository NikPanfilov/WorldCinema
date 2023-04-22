package com.nikpanfilov.collections.presentation

sealed class CollectionsState {

	object Initial : CollectionsState()

	data class Content(val sendState: CollectionsSendState) : CollectionsState()
}

sealed class CollectionsSendState {

	object Input : CollectionsSendState()

	object Loading : CollectionsSendState()

	object Success : CollectionsSendState()

	data class Error(val errorCode: Int) : CollectionsSendState()
}