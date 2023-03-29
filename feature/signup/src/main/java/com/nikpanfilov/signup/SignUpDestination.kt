package com.nikpanfilov.signup

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.signup.ui.SignUpFragment

class SignUpDestination : FragmentDestination {

	override fun createInstance() = SignUpFragment.newInstance()
}