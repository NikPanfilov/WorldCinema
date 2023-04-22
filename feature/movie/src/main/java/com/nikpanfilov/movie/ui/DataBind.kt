package com.nikpanfilov.movie.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.movie.R
import com.nikpanfilov.movie.databinding.FragmentMovieBinding
import com.nikpanfilov.movie.databinding.TagItemBinding
import com.nikpanfilov.movie.presentation.MovieViewModel
import com.nikpanfilov.movie.ui.adapter.EpisodesAdapter
import com.nikpanfilov.movie.ui.adapter.ShotsAdapter

internal fun FragmentMovieBinding.bindData(viewModel: MovieViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		val context = root.context

		coverImageView.setImage(movie.poster)

		backButton.setOnClickListener { viewModel.navigateBack() }

		if (episodesFlow.value?.isNotEmpty() == true) {
			watchButton.setOnClickListener { navigateToEpisode(episodesFlow.value!![0]) }
			watchButton.visibility = View.VISIBLE
		} else {
			watchButton.visibility = View.GONE
		}

		ageTextView.text = movie.age
		ageTextView.setAgeColor(context)

		commentsButton.setOnClickListener { navigateToChat() }

		tagsFlexBox.removeAllViews()
		viewModel.movie.tags.forEach {
			val tagView = TagItemBinding.inflate(LayoutInflater.from(context), tagsFlexBox, false)
			tagView.tagTextView.text = it.tagName
			tagsFlexBox.addView(tagView.root)
		}

		descriptionTextView.text = movie.description

		val shotsAdapter = ShotsAdapter()
		shotsRecyclerView.adapter = shotsAdapter
		shotsAdapter.data = movie.images
		if (movie.images.isEmpty()) {
			setShotsViewVisibility(View.GONE)
		} else {
			setShotsViewVisibility(View.VISIBLE)
		}

		val episodesAdapter = EpisodesAdapter(viewModel::navigateToEpisode)
		episodesRecyclerView.adapter = episodesAdapter
		viewModel.episodesFlow.launch(scope) {
			if (it != null && it.isNotEmpty()) {
				episodesAdapter.data = it
				setEpisodesViewVisibility(View.VISIBLE)

				val firstEpisode = it[0]
				watchButton.setOnClickListener { navigateToEpisode(firstEpisode) }
				watchButton.visibility = View.VISIBLE
			} else {
				setEpisodesViewVisibility(View.GONE)
				watchButton.visibility = View.GONE
			}
		}
	}
}

private fun FragmentMovieBinding.setShotsViewVisibility(visibility: Int) {
	shotsRecyclerView.visibility = visibility
	shotsTitleTextView.visibility = visibility
}

private fun FragmentMovieBinding.setEpisodesViewVisibility(visibility: Int) {
	episodesRecyclerView.visibility = visibility
	episodesTitleTextView.visibility = visibility
}

private fun TextView.setAgeColor(context: Context) {
	this.setTextColor(
		resources.getColor(
			when (this.text.toString()) {
				context.getString(R.string.age_18) -> R.color.age_18
				context.getString(R.string.age_16) -> R.color.age_16
				context.getString(R.string.age_12) -> R.color.age_12
				context.getString(R.string.age_6)  -> R.color.age_6
				else                               -> R.color.age_0
			}
		)
	)
}