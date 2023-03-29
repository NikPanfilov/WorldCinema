package com.nikpanfilov.main.domain.entity

data class Movie(
	val movieId: String,
	val name: String,
	val description: String,
	val age: String,
	val chatInfo: ChatInfo,
	val images: List<String>,
	val poster: String,
	val tags: List<Tag>
)

data class Tag(
	val tagId: String,
	val tagName: String,
	val categoryName: String
)

data class ChatInfo(
	val chatId: String,
	val chatName: String
)
