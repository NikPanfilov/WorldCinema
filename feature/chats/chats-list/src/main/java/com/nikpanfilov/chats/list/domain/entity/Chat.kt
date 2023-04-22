package com.nikpanfilov.chats.list.domain.entity

data class Chat(
	val chatId: String, val chatName: String, val lastMessage: LastMessage,
)

data class LastMessage(
	val messageId: String,
	val creationDateTime: String,
	val authorId: String,
	val authorName: String,
	val authorAvatar: String,
	val text: String
)