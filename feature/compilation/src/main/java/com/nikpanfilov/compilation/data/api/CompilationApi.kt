package com.nikpanfilov.compilation.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface CompilationApi {

	@GET("api/movies/{movieId}/dislike")
	suspend fun dislike(@Path("movieId") movieId: String)
}