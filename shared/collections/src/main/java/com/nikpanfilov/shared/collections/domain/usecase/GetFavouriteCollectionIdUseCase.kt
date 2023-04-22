package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.FAVOURITE
import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository

class GetFavouriteCollectionIdUseCase(private val repository: CollectionsRepository) {

	suspend operator fun invoke(): String =
		repository.getCollections().find { it.name == FAVOURITE }?.collectionId ?: CreateFavouriteCollectionUseCase(repository).invoke().collectionId
}