package com.nikpanfilov.shared.collections.data.repository

import com.nikpanfilov.shared.collections.data.datasource.CollectionsDataSource
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm
import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository

class CollectionsRepositoryImpl(private val dataSource: CollectionsDataSource) : CollectionsRepository {

	override suspend fun getCollections(): List<Collection> = dataSource.getCollections()

	override suspend fun deleteCollections(id: String) = dataSource.deleteCollections(id)

	override suspend fun createCollection(collection: CollectionForm): Collection = dataSource.createCollection(collection)
}