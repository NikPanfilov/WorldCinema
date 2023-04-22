package com.nikpanfilov.chat.data.datasource

import com.nikpanfilov.chat.data.dto.MessageDto
import com.nikpanfilov.chat.data.mapper.toEntity
import com.nikpanfilov.chat.domain.entity.Message
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatDataSourceImpl(private val client: OkHttpClient, private val moshi: Moshi) : ChatDataSource {

	private val messages = MutableStateFlow<List<Message>>(listOf())

	private lateinit var webSocket: WebSocket

	override fun connect(id: String) {
		val request = Request.Builder().url("ws://107684.web.hosting-russia.ru:8000/api/chats/$id/messages").build()
		webSocket = client.newWebSocket(request, listener)
	}

	private val listener = object : WebSocketListener() {

		override fun onMessage(webSocket: WebSocket, text: String) {
			val message = moshi.adapter(MessageDto::class.java).fromJson(text)
			val list = messages.value.toMutableList()
			if (message != null) {
				list.add(message.toEntity())
			}
			messages.value = list
		}
	}

	override fun disconnect() {
		webSocket.close(NORMAL, null)
	}

	override fun sendMessage(text: String) {
		webSocket.send(text)
	}

	override fun getMessages(): StateFlow<List<Message>> = messages

	companion object {

		const val NORMAL = 1000
	}
}