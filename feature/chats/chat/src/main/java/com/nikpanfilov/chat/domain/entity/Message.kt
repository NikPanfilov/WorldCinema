package com.nikpanfilov.chat.domain.entity

data class Message(
	val messageId: String,
	val creationDateTime: String,
	val authorId: String,
	val authorName: String,
	val authorAvatar: String?,
	val text: String
)
