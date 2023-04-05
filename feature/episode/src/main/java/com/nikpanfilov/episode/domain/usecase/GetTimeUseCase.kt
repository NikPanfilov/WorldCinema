package com.nikpanfilov.episode.domain.usecase

import com.nikpanfilov.episode.domain.entity.EpisodeTime
import com.nikpanfilov.episode.domain.repository.TimeRepository

class GetTimeUseCase(private val repository: TimeRepository) {

	suspend operator fun invoke(episodeId: String): EpisodeTime = repository.getTime(episodeId)
}