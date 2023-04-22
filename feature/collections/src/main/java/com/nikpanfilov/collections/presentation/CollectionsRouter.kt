package com.nikpanfilov.collections.presentation

import com.nikpanfilov.shared.collections.domain.entity.Collection

interface CollectionsRouter {

	fun navigateToCollectionCreate(collection: Collection)
	fun navigateToMovies(collection: Collection)
}