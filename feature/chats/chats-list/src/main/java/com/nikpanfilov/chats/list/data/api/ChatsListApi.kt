package com.nikpanfilov.chats.list.data.api

import com.nikpanfilov.chats.list.data.dto.ChatDto
import retrofit2.http.GET

interface ChatsListApi {

	@GET("api/chats")
	suspend fun getChats(): List<ChatDto>
}