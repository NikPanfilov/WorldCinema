package com.nikpanfilov.worldcinema.presentation

import androidx.lifecycle.ViewModel

class MainViewModel(
    private val router: MainActivityRouter
) : ViewModel() {

    fun toSplash() {
        router.navigateToSplash()
    }
}
