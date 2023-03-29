package com.nikpanfilov.core.navigation

import androidx.fragment.app.Fragment

interface FragmentDestination {

	fun createInstance(): Fragment
}