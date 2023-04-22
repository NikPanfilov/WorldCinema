package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.collections.CollectionsDestination
import com.nikpanfilov.compilation.CompilationDestination
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.main.MainDestination
import com.nikpanfilov.profile.ProfileDestination
import com.nikpanfilov.splash.SplashDestination
import com.nikpanfilov.worldcinema.presentation.MainActivityRouter

class MainActivityRouterImpl(
	private val router: GlobalRouter
) : MainActivityRouter {

	override fun navigateToSplash() {
		router.open(SplashDestination())
	}

	override fun navigateToMain() {
		router.open(MainDestination())
	}

	override fun navigateToCompilation() {
		router.open(CompilationDestination())
	}

	override fun navigateToCollections() {
		router.open(CollectionsDestination())
	}

	override fun navigateToProfile() {
		router.open(ProfileDestination())
	}
}