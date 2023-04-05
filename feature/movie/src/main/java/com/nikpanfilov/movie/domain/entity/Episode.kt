package com.nikpanfilov.movie.domain.entity

import com.nikpanfilov.core.navigation.holders.EpisodeHolder

data class Episode(
	val episodeId: String,
	val name: String,
	val description: String,
	val director: String,
	val stars: List<String>,
	val year: String,
	val images: List<String>,
	val runtime: Int,
	val preview: String,
	val filePath: String
)

internal fun Episode.toEpisodeHolder() = EpisodeHolder(
	episodeId = episodeId,
	name = name,
	description = description,
	director = director,
	stars = stars,
	year = year,
	images = images,
	runtime = runtime,
	preview = preview,
	filePath = filePath
)
