package com.nikpanfilov.episode.domain.entity

import com.nikpanfilov.core.navigation.holders.EpisodeHolder

data class Episode(
	val episodeId: String,
	val name: String,
	val description: String,
	val filePath: String,
	val movieId: String,
	val movieName: String,
	val moviePoster: String,
	val movieYears: String,
	val chatId: String,
	val chatName:String
)

internal fun EpisodeHolder.toEpisode() =
	Episode(
		episodeId = episodeId,
		name = name,
		description = description,
		filePath = filePath,
		movieId = movieId,
		movieName = movieName,
		moviePoster = moviePoster,
		movieYears = movieYears,
		chatId = chatId,
		chatName = chatName
	)
