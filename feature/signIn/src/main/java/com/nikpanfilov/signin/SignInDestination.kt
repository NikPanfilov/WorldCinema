package com.nikpanfilov.signin

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.signin.ui.SignInFragment

class SignInDestination : FragmentDestination {

	override fun createInstance() = SignInFragment.newInstance()
}