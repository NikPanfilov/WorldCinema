package com.nikpanfilov.chat.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageDto(
	@Json(name = "messageId") val messageId: String,
	@Json(name = "creationDateTime") val creationDateTime: String,
	@Json(name = "authorId") val authorId: String,
	@Json(name = "authorName") val authorName: String,
	@Json(name = "authorAvatar") val authorAvatar: String?,
	@Json(name = "text") val text: String,
)