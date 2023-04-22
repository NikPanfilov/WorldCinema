package com.nikpanfilov.collections.presentation

import com.nikpanfilov.shared.collections.domain.entity.Collection

interface CollectionMoviesRouter {

	fun navigateBack()
	fun navigateToCollectionEdit(collection: Collection)
}