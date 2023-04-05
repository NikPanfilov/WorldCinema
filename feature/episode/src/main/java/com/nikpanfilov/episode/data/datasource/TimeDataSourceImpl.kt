package com.nikpanfilov.episode.data.datasource

import com.nikpanfilov.episode.data.api.TimeApi
import com.nikpanfilov.episode.data.mapper.toDto
import com.nikpanfilov.episode.data.mapper.toEntity
import com.nikpanfilov.episode.domain.entity.EpisodeTime

class TimeDataSourceImpl(private val api: TimeApi) : TimeDataSource {

	override suspend fun getTime(episodeId: String): EpisodeTime = api.getTime(episodeId).toEntity()

	override suspend fun setTime(episodeId: String, time: EpisodeTime) = api.setTime(episodeId, time.toDto())
}