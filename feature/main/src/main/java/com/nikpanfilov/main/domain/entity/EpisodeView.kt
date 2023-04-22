package com.nikpanfilov.main.domain.entity

data class EpisodeView(
	val episodeId: String,
	val movieId: String,
	val episodeName: String,
	val movieName: String,
	val preview: String,
	val filePath: String,
	val time: Long
)