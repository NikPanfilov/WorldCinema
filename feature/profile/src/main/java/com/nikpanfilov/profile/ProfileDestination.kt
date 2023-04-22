package com.nikpanfilov.profile

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.profile.ui.ProfileFragment

class ProfileDestination() : FragmentDestination {

	override fun createInstance() = ProfileFragment.newInstance()
}