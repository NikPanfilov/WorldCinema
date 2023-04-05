package com.nikpanfilov.movie.data.datasource

import com.nikpanfilov.movie.domain.entity.Episode

interface EpisodesDataSource {

	suspend fun getEpisodes(movieId: String): List<Episode>
}