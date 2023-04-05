package com.nikpanfilov.movie.domain.entity

import com.nikpanfilov.core.navigation.holders.MovieHolder

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

internal fun MovieHolder.toMovie(): Movie =
	Movie(
		movieId = movieId,
		name = name,
		description = description,
		age = age,
		chatInfo = ChatInfo(chatId = chatInfo.chatId, chatName = chatInfo.chatName),
		images = images,
		poster = poster,
		tags = tags.map { Tag(tagId = it.tagId, tagName = it.tagName, categoryName = it.categoryName) }
	)
