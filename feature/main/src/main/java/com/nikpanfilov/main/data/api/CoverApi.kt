package com.nikpanfilov.main.data.api

import com.nikpanfilov.main.data.dto.CoverDto
import retrofit2.http.GET

interface CoverApi {

	@GET("api/cover")
	suspend fun getCover(): CoverDto
}