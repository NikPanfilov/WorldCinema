package com.nikpanfilov.chats.list.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatDto(
	@Json(name = "chatId") val chatId: String,
	@Json(name = "chatName") val chatName: String,
	@Json(name = "lastMessage") val lastMessage: LastMessageDto,
)

@JsonClass(generateAdapter = true)
data class LastMessageDto(
	@Json(name = "messageId") val messageId: String,
	@Json(name = "creationDateTime") val creationDateTime: String,
	@Json(name = "authorId") val authorId: String,
	@Json(name = "authorName") val authorName: String,
	@Json(name = "authorAvatar") val authorAvatar: String,
	@Json(name = "text") val text: String
)