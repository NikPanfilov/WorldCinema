package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository

class GetCollectionsUseCase(private val repository: CollectionsRepository) {

	suspend operator fun invoke(): List<Collection> = repository.getCollections()
}