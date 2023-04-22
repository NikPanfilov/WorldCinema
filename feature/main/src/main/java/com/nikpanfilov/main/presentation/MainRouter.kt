package com.nikpanfilov.main.presentation

import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieHolder

interface MainRouter {

	fun navigateToMovie(movie: MovieHolder)

	fun navigateToEpisode(episode: EpisodeHolder)
}