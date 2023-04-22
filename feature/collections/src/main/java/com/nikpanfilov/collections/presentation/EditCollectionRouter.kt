package com.nikpanfilov.collections.presentation

import com.nikpanfilov.shared.collections.domain.entity.Collection

interface EditCollectionRouter {

	fun navigateToIconChoose(collection: Collection)
	fun navigateBack(collection: Collection)
	fun navigateToCollections()
}