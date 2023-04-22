package com.nikpanfilov.shared.collections.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionDto(
	@Json(name = "collectionId") val collectionId: String,
	@Json(name = "name") val name: String
)
