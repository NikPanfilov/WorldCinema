package com.nikpanfilov.main.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoverDto(
	@Json(name = "backgroundImage") val backgroundImage: String,
	@Json(name = "foregroundImage") val foregroundImage: String
)
