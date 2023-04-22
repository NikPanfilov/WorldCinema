package com.nikpanfilov.shared.collections.data.api

import com.nikpanfilov.shared.collections.data.dto.CollectionDto
import com.nikpanfilov.shared.collections.data.dto.CollectionFormDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectionsApi {

	@GET("api/collections")
	suspend fun getCollections(): List<CollectionDto>

	@POST("api/collections")
	suspend fun createCollection(@Body collection: CollectionFormDto): CollectionDto

	@DELETE("api/collections/{collectionId}")
	suspend fun deleteCollections(@Path("collectionId") id: String)
}