package com.nikpanfilov.main.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDto(
	@Json(name = "movieId") val movieId: String,
	@Json(name = "name") val name: String,
	@Json(name = "description") val description: String,
	@Json(name = "age") val age: String,
	@Json(name = "chatInfo") val chatInfo: ChatInfoDto,
	@Json(name = "imageUrls") val images: List<String>,
	@Json(name = "poster") val poster: String,
	@Json(name = "tags") val tags: List<TagDto>
)

@JsonClass(generateAdapter = true)
data class TagDto(
	@Json(name = "tagId") val tagId: String,
	@Json(name = "tagName") val tagName: String,
	@Json(name = "categoryName") val categoryName: String
)

@JsonClass(generateAdapter = true)
data class ChatInfoDto(
	@Json(name = "chatId") val chatId: String,
	@Json(name = "chatName") val chatName: String
)