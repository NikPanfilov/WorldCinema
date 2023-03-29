package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.main.MainDestination
import com.nikpanfilov.signin.presentation.SignInRouter
import com.nikpanfilov.signup.SignUpDestination

class SignInRouterImpl(
	private val router: GlobalRouter
) : SignInRouter {

	override fun navigateToMain() {
		router.open(MainDestination())
	}

	override fun navigateToSignUp() {
		router.open(SignUpDestination())
	}
}