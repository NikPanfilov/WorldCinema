package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.chats.list.ChatsListDestination
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.profile.presentation.ProfileRouter
import com.nikpanfilov.signin.SignInDestination

class ProfileRouterImpl(private val router: GlobalRouter) : ProfileRouter {

	override fun navigateToSignIn() {
		router.open(SignInDestination())
	}

	override fun navigateToChats() {
		router.open(ChatsListDestination())
	}
}