package com.nikpanfilov.episode.domain.entity

import com.nikpanfilov.core.navigation.holders.MovieInfoHolder

data class MovieInfo(
	val id: String, val name: String, val poster: String, val years: String
)

internal fun MovieInfoHolder.toMovieInfo() = MovieInfo(
	id = id, name = name, poster = poster, years = years
)
