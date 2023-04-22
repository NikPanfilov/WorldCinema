package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm
import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository

class CreateCollectionUseCase(private val repository: CollectionsRepository) {

	suspend operator fun invoke(collection: CollectionForm): Collection = repository.createCollection(collection)
}