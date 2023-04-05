package com.nikpanfilov.episode.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.core.navigation.holders.MovieInfoHolder
import com.nikpanfilov.episode.databinding.FragmentEpisodeBinding
import com.nikpanfilov.episode.presentation.EpisodeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodeFragment : Fragment() {

	companion object {

		private const val EPISODE = "episode"
		private const val MOVIE = "movie"

		fun newInstance(episode: EpisodeHolder, movie: MovieInfoHolder) = EpisodeFragment().apply {
			arguments = bundleOf(EPISODE to episode, MOVIE to movie)
		}
	}

	private val binding by lazy { FragmentEpisodeBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<EpisodeViewModel> {
		parametersOf(arguments?.get(EPISODE), arguments?.get(MOVIE))
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)

		return binding.root
	}
}