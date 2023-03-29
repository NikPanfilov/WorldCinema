package com.nikpanfilov.worldcinema.navigation

import android.util.Log
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.main.MainDestination
import com.nikpanfilov.signin.SignInDestination
import com.nikpanfilov.signup.SignUpDestination
import com.nikpanfilov.splash.presentation.SplashRouter

class SplashRouterImpl(
	private val router: GlobalRouter
) : SplashRouter {

	override fun navigateToSignInScreen() {
		router.open(SignInDestination())
	}

	override fun navigateToSignUpScreen() {
		router.open(SignUpDestination())
	}

	override fun navigateToMainScreen() {
		router.open(MainDestination())
	}
}
