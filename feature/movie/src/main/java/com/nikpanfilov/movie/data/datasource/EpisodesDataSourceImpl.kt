package com.nikpanfilov.movie.data.datasource

import com.nikpanfilov.movie.data.api.EpisodesApi
import com.nikpanfilov.movie.data.mapper.toEntity
import com.nikpanfilov.movie.domain.entity.Episode

class EpisodesDataSourceImpl(private val api: EpisodesApi) : EpisodesDataSource {

	override suspend fun getEpisodes(movieId: String): List<Episode> = api.getEpisodes(movieId).map { it.toEntity() }
}