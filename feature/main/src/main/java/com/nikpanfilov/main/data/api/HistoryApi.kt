package com.nikpanfilov.main.data.api

import com.nikpanfilov.main.data.dto.EpisodeViewDto
import retrofit2.http.GET

interface HistoryApi {

	@GET("api/history")
	suspend fun getHistory(): List<EpisodeViewDto>
}