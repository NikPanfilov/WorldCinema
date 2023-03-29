package com.nikpanfilov.main

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.main.ui.MainFragment

class MainDestination() : FragmentDestination {

	override fun createInstance() = MainFragment.newInstance()
}