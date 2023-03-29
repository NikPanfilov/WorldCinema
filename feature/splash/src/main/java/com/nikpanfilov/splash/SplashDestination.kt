package com.nikpanfilov.splash

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.splash.ui.SplashFragment

class SplashDestination () : FragmentDestination {

	override fun createInstance() = SplashFragment.newInstance()
}