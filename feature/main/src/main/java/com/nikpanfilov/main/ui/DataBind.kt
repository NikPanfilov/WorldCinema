package com.nikpanfilov.main.ui

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.extensions.setImage
import com.nikpanfilov.main.databinding.FragmentMainBinding
import com.nikpanfilov.main.presentation.MainViewModel
import com.nikpanfilov.main.ui.adapter.PosterAdapter

internal fun FragmentMainBinding.bindData(viewModel: MainViewModel, scope: LifecycleCoroutineScope) {
	val trendAdapter = PosterAdapter(viewModel::navigateToMovie)
	trendRecyclerView.adapter = trendAdapter
	viewModel.trendMoviesMutableFlow.launch(scope) {
		if (it.isEmpty())
			trendViewsVisibility(View.GONE)
		else
			trendViewsVisibility(View.VISIBLE)
		trendAdapter.data = it
	}

	val newMovieAdapter = PosterAdapter(viewModel::navigateToMovie)
	newMovieRecyclerView.adapter = newMovieAdapter
	viewModel.newMoviesMutableFlow.launch(scope) {
		if (it.isEmpty())
			newViewsVisibility(View.GONE)
		else
			newViewsVisibility(View.VISIBLE)
		newMovieAdapter.data = it
	}

	val forYouAdapter = PosterAdapter(viewModel::navigateToMovie)
	forYouRecyclerView.adapter = forYouAdapter
	viewModel.forYouMoviesMutableFlow.launch(scope) {
		if (it.isEmpty())
			forYouViewsVisibility(View.GONE)
		else
			forYouViewsVisibility(View.VISIBLE)
		forYouAdapter.data = it
	}

	viewModel.lastViewMutableFlow.launch(scope) {
		if (it.isNotEmpty()) {
			lastViewsVisibility(View.VISIBLE)
			lastViewImageView.setImage(it[0].poster)
		} else {
			lastViewsVisibility(View.GONE)
		}
	}
	toLastViewButton.setOnClickListener { viewModel.navigateToLastViewEpisode() }

	viewModel.coverMutableFlow.launch(scope) {
		if (it != null) {
			coverImageView.setImage(it.backgroundImage)
		}
	}
}

private fun FragmentMainBinding.lastViewsVisibility(visibility: Int) {
	lastViewTextView.visibility = visibility
	toLastViewButton.visibility = visibility
	lastViewImageView.visibility = visibility
}

private fun FragmentMainBinding.trendViewsVisibility(visibility: Int) {
	inTrendTextView.visibility = visibility
	trendRecyclerView.visibility = visibility
}

private fun FragmentMainBinding.newViewsVisibility(visibility: Int) {
	newMovieRecyclerView.visibility = visibility
	newMovieTextView.visibility = visibility
}

private fun FragmentMainBinding.forYouViewsVisibility(visibility: Int) {
	forYouRecyclerView.visibility = visibility
	forYouTextView.visibility = visibility
}
