package com.nikpanfilov.episode.domain.usecase

import com.nikpanfilov.episode.domain.entity.EpisodeTime
import com.nikpanfilov.episode.domain.repository.TimeRepository

class SetTimeUseCase(private val repository: TimeRepository) {

	suspend operator fun invoke(episodeId: String, time: EpisodeTime) = repository.setTime(episodeId, time)
}