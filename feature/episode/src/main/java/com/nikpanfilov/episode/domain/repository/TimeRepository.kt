package com.nikpanfilov.episode.domain.repository

import com.nikpanfilov.episode.domain.entity.EpisodeTime

interface TimeRepository {

	suspend fun getTime(episodeId: String): EpisodeTime

	suspend fun setTime(episodeId: String, time: EpisodeTime)
}