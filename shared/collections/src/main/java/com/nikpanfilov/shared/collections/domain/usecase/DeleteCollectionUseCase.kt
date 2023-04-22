package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository

class DeleteCollectionUseCase(private val repository: CollectionsRepository) {

	suspend operator fun invoke(id: String) = repository.deleteCollections(id)
}