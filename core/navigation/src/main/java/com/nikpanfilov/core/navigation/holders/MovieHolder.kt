package com.nikpanfilov.core.navigation.holders

import java.io.Serializable

data class MovieHolder(
	val movieId: String,
	val name: String,
	val description: String,
	val age: String,
	val chatInfo: ChatInfoHolder,
	val images: List<String>,
	val poster: String,
	val tags: List<TagHolder>
) : Serializable

data class TagHolder(
	val tagId: String,
	val tagName: String,
	val categoryName: String
) : Serializable

data class ChatInfoHolder(
	val chatId: String,
	val chatName: String
) : Serializable
