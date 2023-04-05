package com.nikpanfilov.movie.data.api

import com.nikpanfilov.movie.data.dto.EpisodeDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApi {

	@GET("api/movies/{movieId}/episodes")
	suspend fun getEpisodes(@Path("movieId") movieId: String): List<EpisodeDto>
}