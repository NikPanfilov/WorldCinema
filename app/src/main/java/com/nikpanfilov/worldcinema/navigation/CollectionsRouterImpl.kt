package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.collections.ChooseIconDestination
import com.nikpanfilov.collections.CollectionMoviesDestination
import com.nikpanfilov.collections.CollectionsDestination
import com.nikpanfilov.collections.EditCollectionDestination
import com.nikpanfilov.collections.presentation.ChooseIconRouter
import com.nikpanfilov.collections.presentation.CollectionMoviesRouter
import com.nikpanfilov.collections.presentation.CollectionsRouter
import com.nikpanfilov.collections.presentation.EditCollectionRouter
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.shared.collections.domain.entity.Collection

class CollectionsRouterImpl(private val router: GlobalRouter) : CollectionsRouter {

	override fun navigateToCollectionCreate(collection: Collection) {
		router.open(EditCollectionDestination(collection))
	}

	override fun navigateToMovies(collection: Collection) {
		router.open(CollectionMoviesDestination(collection))
	}
}

class CollectionMoviesRouterImpl(private val router: GlobalRouter) : CollectionMoviesRouter {

	override fun navigateBack() {
		router.exit()
	}

	override fun navigateToCollectionEdit(collection: Collection) {
		router.open(EditCollectionDestination(collection))
	}
}

class EditCollectionRouterImpl(private val router: GlobalRouter) : EditCollectionRouter {

	override fun navigateToIconChoose(collection: Collection) {
		router.open(ChooseIconDestination(collection))
	}

	override fun navigateBack(collection: Collection) {
		router.open(CollectionMoviesDestination(collection))
	}

	override fun navigateToCollections() {
		router.backTo(CollectionsDestination())
	}
}

class ChooseIconRouterImpl(private val router: GlobalRouter) : ChooseIconRouter {

	override fun navigateBack(collection: Collection) {
		router.open(EditCollectionDestination(collection))
	}

	override fun navigateBack() {
		router.exit()
	}
}