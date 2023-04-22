package com.nikpanfilov.main.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeViewDto(
	@Json(name = "episodeId") val episodeId: String,
	@Json(name = "movieId") val movieId: String,
	@Json(name = "episodeName") val episodeName: String,
	@Json(name = "movieName") val movieName: String,
	@Json(name = "preview") val preview: String,
	@Json(name = "filePath") val filePath: String,
	@Json(name = "time") val time: Long,
)
