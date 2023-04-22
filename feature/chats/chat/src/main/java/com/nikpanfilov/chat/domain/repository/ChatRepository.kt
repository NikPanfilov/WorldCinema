package com.nikpanfilov.chat.domain.repository

import com.nikpanfilov.chat.domain.entity.Message
import kotlinx.coroutines.flow.StateFlow

interface ChatRepository {

	fun connect(chatId: String)
	fun disconnect()
	suspend fun getMessages(): StateFlow<List<Message>>
	suspend fun send(text: String)
}