package com.nikpanfilov.movie.data.mapper

import com.nikpanfilov.movie.data.dto.EpisodeDto
import com.nikpanfilov.movie.domain.entity.Episode

internal fun EpisodeDto.toEntity() = Episode(
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