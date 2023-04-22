package com.nikpanfilov.collections

import com.nikpanfilov.collections.ui.CollectionsFragment
import com.nikpanfilov.core.navigation.FragmentDestination

class CollectionsDestination() : FragmentDestination {

	override fun createInstance() = CollectionsFragment.newInstance()
}