package com.nikpanfilov.chat.data.repository

import com.nikpanfilov.chat.data.datasource.ChatDataSource
import com.nikpanfilov.chat.domain.entity.Message
import com.nikpanfilov.chat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.StateFlow

class ChatRepositoryImpl(private val dataSource: ChatDataSource) : ChatRepository {

	override fun connect(chatId: String) {
		dataSource.connect(chatId)
	}

	override fun disconnect() {
		dataSource.disconnect()
	}

	override suspend fun getMessages(): StateFlow<List<Message>> = dataSource.getMessages()

	override suspend fun send(text: String) {
		dataSource.sendMessage(text)
	}
}