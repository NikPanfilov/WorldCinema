package com.nikpanfilov.episode.data.repository

import com.nikpanfilov.episode.data.datasource.TimeDataSource
import com.nikpanfilov.episode.domain.entity.EpisodeTime
import com.nikpanfilov.episode.domain.repository.TimeRepository

class TimeRepositoryImpl(private val dataSource: TimeDataSource) : TimeRepository {

	override suspend fun getTime(episodeId: String): EpisodeTime = dataSource.getTime(episodeId)

	override suspend fun setTime(episodeId: String, time: EpisodeTime) = dataSource.setTime(episodeId, time)
}