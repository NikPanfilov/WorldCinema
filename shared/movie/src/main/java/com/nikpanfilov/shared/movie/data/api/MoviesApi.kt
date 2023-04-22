package com.nikpanfilov.shared.movie.data.api

import com.nikpanfilov.shared.movie.MovieDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

	@GET("api/movies")
	suspend fun getMovies(@Query("filter") filter: String): List<MovieDto>
}