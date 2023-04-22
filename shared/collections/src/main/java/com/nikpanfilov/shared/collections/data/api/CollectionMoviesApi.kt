package com.nikpanfilov.shared.collections.data.api

import com.nikpanfilov.shared.collections.data.dto.MovieDto
import com.nikpanfilov.shared.collections.data.dto.MovieValueDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionMoviesApi {

	@GET("api/collections/{collectionId}/movies")
	suspend fun getMovieCollection(@Path("collectionId") id: String): List<MovieDto>

	@POST("api/collections/{collectionId}/movies")
	suspend fun addToCollection(@Path("collectionId") id: String, @Body movie: MovieValueDto)

	@DELETE("api/collections/{collectionId}/movies")
	suspend fun deleteFromCollection(@Path("collectionId") collectionId: String, @Query("movieId") movieId: String)
}