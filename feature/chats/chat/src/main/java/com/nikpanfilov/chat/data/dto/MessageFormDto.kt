package com.nikpanfilov.chat.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MessageFormDto(
	@Json(name = "text") val text: String,
)