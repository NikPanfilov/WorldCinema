package com.nikpanfilov.compilation.ui

import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.compilation.databinding.FragmentCompilationBinding
import com.nikpanfilov.compilation.presentation.CompilationViewModel
import com.nikpanfilov.extensions.launch

internal fun FragmentCompilationBinding.bindData(viewModel: CompilationViewModel, scope: LifecycleCoroutineScope) {
	with(viewModel) {
		moviesFlow.launch(scope) {
			if (it.isEmpty()) {
				noMoviesImageView.visibility = View.VISIBLE
				noMoviesTextView.visibility = View.VISIBLE
			} else {
				noMoviesImageView.visibility = View.GONE
				noMoviesTextView.visibility = View.GONE
			}
		}

		dislikeButton.setOnClickListener {
			dislike(moviesFlow.value[0].movieId)
		}
		addToFavouriteButton.setOnClickListener {
			toFavourite(moviesFlow.value[0].movieId)
		}
		playButton.setOnClickListener {
			navigateToMovie(moviesFlow.value[0])
		}
	}
}