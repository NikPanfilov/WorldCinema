package com.nikpanfilov.main.ui

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.bumptech.glide.Glide
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.main.databinding.FragmentMainBinding
import com.nikpanfilov.main.presentation.MainViewModel
import com.nikpanfilov.main.ui.adapter.PosterAdapter

internal fun FragmentMainBinding.bindData(viewModel: MainViewModel, scope: LifecycleCoroutineScope) {
	val trendAdapter = PosterAdapter(viewModel::navigateToMovie, false)
	trendRecyclerView.adapter = trendAdapter
	viewModel.trendMoviesMutableFlow.launch(scope) {
		trendAdapter.data = it
	}

	val newMovieAdapter = PosterAdapter(viewModel::navigateToMovie, true)
	newMovieRecyclerView.adapter = newMovieAdapter
	viewModel.newMoviesMutableFlow.launch(scope) {
		newMovieAdapter.data = it
	}

	val forYouAdapter = PosterAdapter(viewModel::navigateToMovie, false)
	forYouRecyclerView.adapter = forYouAdapter
	viewModel.forYouMoviesMutableFlow.launch(scope) {
		forYouAdapter.data = it
	}

	viewModel.lastViewMutableFlow.launch(scope) {
		if (it.isNotEmpty()) {
			lastViewVisibility(View.VISIBLE)
			Glide.with(lastViewImageView)
				.load(it[0].poster)
				.into(lastViewImageView)
		} else {
			lastViewVisibility(View.GONE)
		}
	}
	toLastViewButton.setOnClickListener { viewModel.navigateToLastViewEpisode() }

	viewModel.coverMutableFlow.launch(scope) {
		if (it != null)
			Glide.with(coverImageView)
				.load(it)
				.into(coverImageView)
	}
}

private fun FragmentMainBinding.lastViewVisibility(visibility: Int) {
	lastViewTextView.visibility = visibility
	toLastViewButton.visibility = visibility
	lastViewImageView.visibility = visibility
}

