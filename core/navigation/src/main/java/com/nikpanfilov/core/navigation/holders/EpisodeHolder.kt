package com.nikpanfilov.core.navigation.holders

import java.io.Serializable

class EpisodeHolder(
	val episodeId: String,
	val name: String,
	val description: String,
	val filePath: String,
	val movieId: String,
	val movieName: String,
	val moviePoster: String,
	val movieYears: String,
	val chatId: String,
	val chatName: String
) : Serializable