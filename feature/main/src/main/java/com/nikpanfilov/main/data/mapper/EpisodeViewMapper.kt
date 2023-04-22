package com.nikpanfilov.main.data.mapper

import com.nikpanfilov.main.data.dto.EpisodeViewDto
import com.nikpanfilov.main.domain.entity.EpisodeView

internal fun EpisodeViewDto.toEntity() = EpisodeView(
	episodeId = episodeId,
	movieId = movieId,
	episodeName = episodeName,
	movieName = movieName,
	preview = preview,
	filePath = filePath,
	time = time
)