package com.nikpanfilov.collections

import com.nikpanfilov.collections.ui.EditCollectionFragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.shared.collections.domain.entity.Collection

class EditCollectionDestination(private val collection: Collection) : FragmentDestination {

	override fun createInstance() = EditCollectionFragment.newInstance(collection)
}