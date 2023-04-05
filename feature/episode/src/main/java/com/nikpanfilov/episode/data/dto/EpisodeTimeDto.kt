package com.nikpanfilov.episode.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeTimeDto(
	@Json(name = "timeInSeconds") val timeInSeconds: Long?
)