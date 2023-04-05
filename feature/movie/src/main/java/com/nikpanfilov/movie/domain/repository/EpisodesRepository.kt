package com.nikpanfilov.movie.domain.repository

import com.nikpanfilov.movie.domain.entity.Episode

interface EpisodesRepository {

	suspend fun getEpisodes(movieId: String): List<Episode>
}