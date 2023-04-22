package com.nikpanfilov.collections

import com.nikpanfilov.collections.ui.ChooseIconFragment
import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.shared.collections.domain.entity.Collection

class ChooseIconDestination(private val collection: Collection) : FragmentDestination {

	override fun createInstance() = ChooseIconFragment.newInstance(collection)
}