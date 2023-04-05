package com.nikpanfilov.worldcinema.navigation

import com.nikpanfilov.core.navigation.GlobalRouter
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieInfoHolder
import com.nikpanfilov.episode.EpisodeDestination
import com.nikpanfilov.movie.presentation.MovieRouter

class MovieRouterImpl(
	private val router: GlobalRouter
) : MovieRouter {

	override fun navigateToEpisode(episode: EpisodeHolder, movieInfo: MovieInfoHolder) {
		router.open(EpisodeDestination(episode, movieInfo))
	}
}