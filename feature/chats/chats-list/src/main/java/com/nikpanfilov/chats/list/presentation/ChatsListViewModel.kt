package com.nikpanfilov.chats.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.chats.list.domain.entity.Chat
import com.nikpanfilov.chats.list.domain.usecase.GetChatsUseCase
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.profile.domain.usecase.GetUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatsListViewModel(private val router: ChatsListRouter, private val getChatsUseCase: GetChatsUseCase, getUserIdUseCase: GetUserIdUseCase) :
	ViewModel() {

	val chatsMutableFlow: MutableStateFlow<List<Chat>> = MutableStateFlow(listOf())

	val userId = getUserIdUseCase()

	private val _stateFlow = MutableStateFlow<ChatsListState>(ChatsListState.Initial)
	val stateFlow: Flow<ChatsListState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? ChatsListState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = ChatsListSendState.Error(code))
	}

	init {
		_stateFlow.value = ChatsListState.Content(ChatsListSendState.Input)
		getChats()
	}

	private fun getChats() {
		viewModelScope.launch(sendErrorHandler) {
			chatsMutableFlow.value = getChatsUseCase()
		}
	}

	fun navigateToChat(id: String, name: String) {
		router.navigateToChat(id, name)
	}

	fun navigateBack() {
		router.navigateBack()
	}
}