package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.main.presentation.MainRouter
import com.nikpanfilov.movie.MovieDestination

class MainRouterImpl(private val router: GlobalRouter) : MainRouter {

	override fun navigateToMovie(movie: MovieHolder) {
		router.open(MovieDestination(movie))
	}

	override fun navigateToEpisode(id: String) {
		//
	}
}