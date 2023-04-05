package com.nikpanfilov.movie

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.movie.ui.MovieFragment

class MovieDestination(private val movie: MovieHolder) : FragmentDestination {

	override fun createInstance() = MovieFragment.newInstance(movie)
}