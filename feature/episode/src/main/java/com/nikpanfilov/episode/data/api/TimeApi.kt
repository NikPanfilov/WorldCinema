package com.nikpanfilov.episode.data.api

import com.nikpanfilov.episode.data.dto.EpisodeTimeDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TimeApi {

	@GET("api/episodes/{episodeId}/time")
	suspend fun getTime(@Path("episodeId") episodeId: String): EpisodeTimeDto

	@POST("api/episodes/{episodeId}/time")
	suspend fun setTime(@Path("episodeId") episodeId: String, @Body time: EpisodeTimeDto)
}