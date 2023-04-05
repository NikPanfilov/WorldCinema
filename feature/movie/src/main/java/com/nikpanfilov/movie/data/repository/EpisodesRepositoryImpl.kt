package com.nikpanfilov.movie.data.repository

import com.nikpanfilov.movie.data.datasource.EpisodesDataSource
import com.nikpanfilov.movie.domain.entity.Episode
import com.nikpanfilov.movie.domain.repository.EpisodesRepository

class EpisodesRepositoryImpl(private val dataSource: EpisodesDataSource) : EpisodesRepository {

	override suspend fun getEpisodes(movieId: String): List<Episode> = dataSource.getEpisodes(movieId)
}