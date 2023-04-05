package com.nikpanfilov.movie.domain.usecase

import com.nikpanfilov.movie.domain.entity.Episode
import com.nikpanfilov.movie.domain.repository.EpisodesRepository

class GetEpisodesUseCase(private val repository: EpisodesRepository) {

	suspend operator fun invoke(movieId: String): List<Episode> = repository.getEpisodes(movieId)
}