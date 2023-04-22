package com.nikpanfilov.collections

import com.nikpanfilov.collections.ui.CollectionMoviesFragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.shared.collections.domain.entity.Collection

class CollectionMoviesDestination(private val collection: Collection) : FragmentDestination {

	override fun createInstance() = CollectionMoviesFragment.newInstance(collection)
}