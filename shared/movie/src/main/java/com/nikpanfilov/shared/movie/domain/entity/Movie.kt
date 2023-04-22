package com.nikpanfilov.shared.movie.domain.entity

import com.nikpanfilov.core.navigation.holders.ChatInfoHolder
import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.core.navigation.holders.TagHolder

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

fun Movie.toMovieHolder() = MovieHolder(
	movieId = movieId,
	name = name,
	description = description,
	age = age,
	chatInfo = ChatInfoHolder(chatId = chatInfo.chatId, chatName = chatInfo.chatName),
	images = images,
	poster = poster,
	tags = tags.map { TagHolder(tagId = it.tagId, tagName = it.tagName, categoryName = it.categoryName) }
)