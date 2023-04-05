package com.nikpanfilov.episode

import com.nikpanfilov.core.navigation.FragmentDestination
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieInfoHolder
import com.nikpanfilov.episode.ui.EpisodeFragment

class EpisodeDestination(private val episode: EpisodeHolder, private val movie: MovieInfoHolder) : FragmentDestination {

	override fun createInstance() = EpisodeFragment.newInstance(episode, movie)
}