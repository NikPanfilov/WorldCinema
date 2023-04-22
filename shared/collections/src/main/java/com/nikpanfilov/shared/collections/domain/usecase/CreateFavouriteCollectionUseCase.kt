package com.nikpanfilov.shared.collections.domain.usecase

import com.nikpanfilov.shared.collections.FAVOURITE
import com.nikpanfilov.shared.collections.domain.entity.Collection
import com.nikpanfilov.shared.collections.domain.entity.CollectionForm
import com.nikpanfilov.shared.collections.domain.repository.CollectionsRepository
import com.nikpanfilov.shared.collections.iconsId

class CreateFavouriteCollectionUseCase(private val repository: CollectionsRepository) {

	suspend operator fun invoke(): Collection = repository.createCollection(
        CollectionForm(iconsId[0].toString() + "//" + FAVOURITE)
    )
}