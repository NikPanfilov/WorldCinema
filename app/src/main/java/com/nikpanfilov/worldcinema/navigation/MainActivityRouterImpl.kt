package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.splash.SplashDestination
import com.nikpanfilov.worldcinema.presentation.MainActivityRouter

class MainActivityRouterImpl(
	private val router: GlobalRouter
) : MainActivityRouter {

	override fun navigateToSplash() {
		router.open(SplashDestination())
	}
}