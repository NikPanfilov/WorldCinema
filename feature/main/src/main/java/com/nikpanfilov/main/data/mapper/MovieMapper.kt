package com.nikpanfilov.main.data.mapper

import com.nikpanfilov.main.data.dto.ChatInfoDto
import com.nikpanfilov.main.data.dto.MovieDto
import com.nikpanfilov.main.data.dto.TagDto
import com.nikpanfilov.main.domain.entity.ChatInfo
import com.nikpanfilov.main.domain.entity.Movie
import com.nikpanfilov.main.domain.entity.Tag

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