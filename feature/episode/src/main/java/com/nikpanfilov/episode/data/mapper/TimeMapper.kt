package com.nikpanfilov.episode.data.mapper

import com.nikpanfilov.episode.data.dto.EpisodeTimeDto
import com.nikpanfilov.episode.domain.entity.EpisodeTime

internal fun EpisodeTimeDto.toEntity() = EpisodeTime(
	timeInSeconds = timeInSeconds
)

internal fun EpisodeTime.toDto() = EpisodeTimeDto(
	timeInSeconds = timeInSeconds
)