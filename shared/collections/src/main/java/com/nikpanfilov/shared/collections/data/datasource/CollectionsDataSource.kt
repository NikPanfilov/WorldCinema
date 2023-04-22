package com.nikpanfilov.shared.collections.data.datasource

import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm

interface CollectionsDataSource {

	suspend fun getCollections(): List<Collection>
	suspend fun createCollection(collection: CollectionForm): Collection
	suspend fun deleteCollections(id: String)
}