package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.main.MainDestination
import com.nikpanfilov.signin.SignInDestination
import com.nikpanfilov.signup.presentation.SignUpRouter

class SignUpRouterImpl(
	private val router: GlobalRouter
) : SignUpRouter {

	override fun navigateToMain() {
		router.open(MainDestination())
	}

	override fun navigateToSignIn() {
		router.open(SignInDestination())
	}
}