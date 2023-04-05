package com.nikpanfilov.movie.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EpisodeDto(
	@Json(name = "episodeId") val episodeId: String,
	@Json(name = "name") val name: String,
	@Json(name = "description") val description: String,
	@Json(name = "director") val director: String,
	@Json(name = "stars") val stars: List<String>,
	@Json(name = "year") val year: String,
	@Json(name = "images") val images: List<String>,
	@Json(name = "runtime") val runtime: Int,
	@Json(name = "preview") val preview: String,
	@Json(name = "filePath") val filePath: String
)