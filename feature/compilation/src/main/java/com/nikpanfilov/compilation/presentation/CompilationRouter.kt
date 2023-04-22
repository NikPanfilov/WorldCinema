package com.nikpanfilov.compilation.presentation

import com.nikpanfilov.core.navigation.holders.MovieHolder

interface CompilationRouter {
	fun navigateToMovie(movie: MovieHolder)
}