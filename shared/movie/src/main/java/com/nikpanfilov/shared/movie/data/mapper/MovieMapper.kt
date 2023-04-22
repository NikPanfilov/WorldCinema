package com.nikpanfilov.shared.movie

import com.nikpanfilov.shared.movie.domain.entity.ChatInfo
import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.nikpanfilov.shared.movie.domain.entity.Tag

internal fun MovieDto.toEntity() = Movie(
	movieId = movieId,
	name = name,
	description = description,
	age = age,
	chatInfo = chatInfo.toEntity(),
	images = images,
	poster = poster,
	tags = tags.map { it.toEntity() }
)

internal fun ChatInfoDto.toEntity() = ChatInfo(chatId = chatId, chatName = chatName)

internal fun TagDto.toEntity() = Tag(tagId = tagId, tagName = tagName, categoryName = categoryName)