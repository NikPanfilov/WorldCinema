package com.nikpanfilov.episode.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.episode.domain.entity.EpisodeTime
import com.nikpanfilov.episode.domain.entity.toEpisode
import com.nikpanfilov.episode.domain.usecase.GetTimeUseCase
import com.nikpanfilov.episode.domain.usecase.SetTimeUseCase
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.MovieValue
import com.nikpanfilov.shared.collections.domain.usecase.AddMovieToCollectionUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetCollectionsUseCase
import com.nikpanfilov.shared.collections.domain.usecase.GetFavouriteCollectionIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EpisodeViewModel(
	private val router: EpisodeRouter,
	private val getTimeUseCase: GetTimeUseCase,
	private val setTimeUseCase: SetTimeUseCase,
	private val addMovieToCollectionUseCase: AddMovieToCollectionUseCase,
	private val getFavouriteCollectionIdUseCase: GetFavouriteCollectionIdUseCase,
	episodeHolder: EpisodeHolder,
	private val getCollectionsUseCase: GetCollectionsUseCase
) : ViewModel() {

	val episode = episodeHolder.toEpisode()
	var episodeTime: Long = 0

	private val _stateFlow = MutableStateFlow<EpisodeState>(EpisodeState.Initial)
	val stateFlow: Flow<EpisodeState>
		get() = _stateFlow.asStateFlow()

	val collectionsFlow: MutableStateFlow<List<Collection>> = MutableStateFlow(listOf())

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? EpisodeState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = EpisodeSendState.Error(code))
	}

	init {
		_stateFlow.value = EpisodeState.Content(EpisodeSendState.Input)

		viewModelScope.launch(sendErrorHandler) {
			episodeTime = getTimeUseCase(episode.episodeId).timeInSeconds ?: 0
		}
		getCollections()
	}

	private fun getCollections(){
		viewModelScope.launch(sendErrorHandler) {
			collectionsFlow.value = getCollectionsUseCase()
		}
	}

	fun addToCollection(collectionId: String) {
		viewModelScope.launch(sendErrorHandler) {
			addMovieToCollectionUseCase(collectionId, MovieValue(episode.movieId))
		}
	}

	fun addToFavourite() {
		viewModelScope.launch(sendErrorHandler) {
			addToCollection(getFavouriteCollectionIdUseCase())
		}
	}

	fun navigateBack(viewedTime: Long) {
		saveViewTime(viewedTime)
		router.navigateBack()
	}

	fun navigateToChat(viewedTime: Long) {
		saveViewTime(viewedTime)
		router.navigateToChat(episode.chatId, episode.chatName)
	}

	private fun saveViewTime(time: Long) {
		viewModelScope.launch(sendErrorHandler) {
			setTimeUseCase(episode.episodeId, EpisodeTime(time))
		}
	}
}