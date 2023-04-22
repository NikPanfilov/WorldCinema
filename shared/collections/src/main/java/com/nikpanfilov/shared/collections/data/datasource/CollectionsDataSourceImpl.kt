package com.nikpanfilov.shared.collections.data.datasource

import com.nikpanfilov.shared.collections.data.api.CollectionsApi
import com.nikpanfilov.shared.collections.data.mapper.toDto
import com.nikpanfilov.shared.collections.data.mapper.toEntity
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm

class CollectionsDataSourceImpl(private val api: CollectionsApi) : CollectionsDataSource {

	override suspend fun getCollections(): List<Collection> = api.getCollections().map { it.toEntity() }

	override suspend fun createCollection(collection: CollectionForm) = api.createCollection(collection.toDto()).toEntity()

	override suspend fun deleteCollections(id: String) = api.deleteCollections(id)
}