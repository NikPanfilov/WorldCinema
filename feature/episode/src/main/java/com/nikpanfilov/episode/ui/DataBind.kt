package com.nikpanfilov.episode.ui

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.nikpanfilov.episode.databinding.FragmentEpisodeBinding
import com.nikpanfilov.episode.presentation.EpisodeViewModel
import com.nikpanfilov.extensions.setImage

internal fun FragmentEpisodeBinding.bindData(viewModel: EpisodeViewModel,) {
	with(viewModel) {
		backButton.setOnClickListener { viewModel.navigateBack(playerView.player.getTime()) }

		episodeTitleTextView.text = episode.name
		descriptionTextView.text = episode.description

		playerView.setVideo(episode.filePath, viewModel.episodeTime)

		commentsButton.setOnClickListener { viewModel.navigateToChat(playerView.player.getTime()) }
		favouriteButton.setOnClickListener { viewModel.addToFavourite() }

		movieTitleTextView.text = episode.movieName
		movieTitleTextView.setOnClickListener { viewModel.navigateBack(playerView.player.getTime()) }
		moviePosterImageView.setImage(
			episode.moviePoster
		)
		moviePosterImageView.setOnClickListener { viewModel.navigateBack(playerView.player.getTime()) }
		yearsTextView.text = episode.movieYears

		/*val spinnerAdapter = ArrayAdapter<String>(root.context, R.layout.spinner_item)
		addToCollectionButton.adapter = spinnerAdapter
		addToCollectionButton.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
				//addToCollection(p0)
			}

			override fun onNothingSelected(p0: AdapterView<*>?) {
				//
			}
		}*/
	}
}

internal fun PlayerView.setVideo(filePath: String, time: Long) {
	val player = ExoPlayer.Builder(context).build()

	val mediaItem = MediaItem.fromUri(filePath)
	val mediaSource = ProgressiveMediaSource.Factory(DefaultDataSource.Factory(context)).createMediaSource(mediaItem)

	player.setMediaSource(mediaSource)
	player.prepare()

	this.player = player

	player.seekTo(0, time)
}

internal fun Player?.getTime() = this?.currentPosition?.div(MILLS_IN_SECOND) ?: 0

const val MILLS_IN_SECOND = 1000