package com.nikpanfilov.movie.presentation

import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieInfoHolder

interface MovieRouter {

	fun navigateToEpisode(episode: EpisodeHolder, movieInfo: MovieInfoHolder)
}