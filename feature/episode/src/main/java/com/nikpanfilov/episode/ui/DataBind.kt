package com.nikpanfilov.episode.ui

import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LifecycleCoroutineScope
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.nikpanfilov.episode.databinding.FragmentEpisodeBinding
import com.nikpanfilov.episode.presentation.EpisodeViewModel
import com.nikpanfilov.extensions.setImage

internal fun FragmentEpisodeBinding.bindData(viewModel: EpisodeViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		val context = root.context

		episodeTitleTextView.text = viewModel.episode.name
		descriptionTextView.text = viewModel.episode.description

		playerView.setVideo(viewModel.episode.filePath, viewModel.episodeTime)

		commentsButton.setOnClickListener { viewModel.navigateToComments(playerView.player.getTime()) }

		movieTitleTextView.text = viewModel.movieInfo.name
		movieTitleTextView.setOnClickListener { viewModel.navigateToMovie(playerView.player.getTime()) }
		moviePosterImageView.setImage(
			viewModel.movieInfo.poster
		)
		moviePosterImageView.setOnClickListener { viewModel.navigateToMovie(playerView.player.getTime()) }
		yearsTextView.text = viewModel.movieInfo.years

		//val spinnerAdapter = ArrayAdapter<String>(context, R.layout.spinner_item, listOf("1", "2", "3", "4"))
		//addToCollectionButton.adapter = spinnerAdapter
		addToCollectionButton.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
				addToCollection()
			}

			override fun onNothingSelected(p0: AdapterView<*>?) {
				//
			}
		}
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