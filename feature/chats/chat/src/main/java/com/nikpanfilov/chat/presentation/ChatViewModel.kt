package com.nikpanfilov.chat.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikpanfilov.chat.domain.entity.DateItem
import com.nikpanfilov.chat.domain.entity.ListItem
import com.nikpanfilov.chat.domain.entity.Message
import com.nikpanfilov.chat.domain.entity.MessageItem
import com.nikpanfilov.chat.domain.entity.UserMessageItem
import com.nikpanfilov.chat.domain.usecase.ConnectToChatUseCase
import com.nikpanfilov.chat.domain.usecase.DisconnectUseCase
import com.nikpanfilov.chat.domain.usecase.GetMessagesUseCase
import com.nikpanfilov.chat.domain.usecase.SendMessageUseCase
import com.nikpanfilov.core.network.utils.CoroutineNetworkExceptionHandler
import com.nikpanfilov.shared.profile.domain.usecase.GetUserIdUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale

class ChatViewModel(
	private val chatId: String,
	private val router: ChatRouter,
	private val connectToChatUseCase: ConnectToChatUseCase,
	private val disconnectUseCase: DisconnectUseCase,
	private val getMessagesUseCase: GetMessagesUseCase,
	private val sendMessageUseCase: SendMessageUseCase,
	private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

	private val userId = getUserIdUseCase()

	private val _stateFlow = MutableStateFlow<ChatState>(ChatState.Initial)
	val stateFlow: Flow<ChatState>
		get() = _stateFlow.asStateFlow()

	private val sendErrorHandler = CoroutineNetworkExceptionHandler { code ->
		val content = _stateFlow.value as? ChatState.Content ?: return@CoroutineNetworkExceptionHandler
		_stateFlow.value = content.copy(sendState = ChatSendState.Error(code))
	}

	var messagesFlow: StateFlow<List<Message>> = MutableStateFlow(listOf())

	init {
		_stateFlow.value = ChatState.Content(ChatSendState.Input)

		connectToChatUseCase(chatId)
		viewModelScope.launch(sendErrorHandler) {
			messagesFlow = getMessagesUseCase()
			Log.i("chat message", messagesFlow.value.toString())
		}
	}

	fun sendMessage(text: String) {
		viewModelScope.launch(sendErrorHandler) {
			sendMessageUseCase(text)
		}
	}

	fun navigateBack() {
		disconnectUseCase()
		router.navigateBack()
	}

	fun List<Message>.toItems(): List<ListItem> {
		val result: MutableList<ListItem> = mutableListOf(DateItem(date = this[0].creationDateTime.formatDate()))
		Log.i("id", userId)
		Log.i("id", "-----------------")

		for (i in indices) {
			val item = this[i]
			Log.i("id", item.authorId)
			val listItem = if (item.authorId == userId) UserMessageItem(
				creationTime = item.creationDateTime.getTime(),
				authorName = item.authorName,
				authorAvatar = item.authorAvatar,
				text = item.text,
				isPrevInChain = if (i == 0) false else this[i - 1].authorId == item.authorId,
				isNextInChain = if (i == size - 1) false else this[i + 1].authorId == item.authorId
			) else {
				MessageItem(
					creationTime = item.creationDateTime.getTime(),
					authorName = item.authorName,
					authorAvatar = item.authorAvatar,
					text = item.text,
					isPrevInChain = if (i == 0) false else this[i - 1].authorId == item.authorId,
					isNextInChain = if (i == size - 1) false else this[i + 1].authorId == item.authorId
				)
			}
			if (i != 0)
				if (!datesIsEqual(item.creationDateTime, this[i - 1].creationDateTime))
					result.add(DateItem(item.creationDateTime.formatDate()))

			result.add(listItem)
		}

		return result
	}

	private fun datesIsEqual(date1: String, date2: String) =
		date1.substringBefore("T") == date2.substringBefore("T")

	private fun String.formatDate(): String {
		val dateTime = LocalDateTime.parse(this)

		Locale.setDefault(Locale.getDefault())
		val calendar: Calendar = Calendar.getInstance()
		calendar.set(Calendar.MONTH, dateTime.month.value)
		val dateFormat = SimpleDateFormat("MMMM", Locale.getDefault())
		val monthName = dateFormat.format(calendar.time)
		val day = dateTime.dayOfMonth

		return "$day $monthName"
	}

	private fun String.getTime(): String {
		val dateTime = LocalDateTime.parse(this)
		return "${dateTime.hour}:${dateTime.minute}"
	}
}