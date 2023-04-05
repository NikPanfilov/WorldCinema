package com.nikpanfilov.episode.data.datasource

import com.nikpanfilov.episode.domain.entity.EpisodeTime

interface TimeDataSource {

	suspend fun getTime(episodeId: String): EpisodeTime

	suspend fun setTime(episodeId: String, time: EpisodeTime)
}