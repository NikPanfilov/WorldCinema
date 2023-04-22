package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.compilation.presentation.CompilationRouter
import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.movie.MovieDestination

class CompilationRouterImpl(private val router: GlobalRouter) : CompilationRouter {

	override fun navigateToMovie(movie: MovieHolder) {
		router.open(MovieDestination(movie))
	}
}