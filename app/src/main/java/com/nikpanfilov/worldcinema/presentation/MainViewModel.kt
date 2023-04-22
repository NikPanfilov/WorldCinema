package com.nikpanfilov.worldcinema.presentation

import androidx.lifecycle.ViewModel

class MainViewModel(
	private val router: MainActivityRouter
) : ViewModel() {

	fun navigateToSplash() {
		router.navigateToSplash()
	}

	fun navigateToMain() {
		router.navigateToMain()
	}

	fun navigateToCompilation() {
		router.navigateToCompilation()
	}

	fun navigateToCollections() {
		router.navigateToCollections()
	}

	fun navigateToProfile() {
		router.navigateToProfile()
	}
}
