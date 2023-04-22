package com.nikpanfilov.chat.data.datasource

import com.nikpanfilov.chat.domain.entity.Message
import kotlinx.coroutines.flow.StateFlow

interface ChatDataSource {

	fun connect(id: String)
	fun disconnect()
	fun sendMessage(text: String)
	fun getMessages(): StateFlow<List<Message>>
}