package com.nikpanfilov.movie.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.core.navigation.holders.MovieHolder
import com.nikpanfilov.movie.databinding.FragmentMovieBinding
import com.nikpanfilov.movie.presentation.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieFragment : Fragment() {

	companion object {

		private const val MOVIE = "movie"

		fun newInstance(movie: MovieHolder) = MovieFragment().apply {
			arguments = bundleOf(MOVIE to movie)
		}
	}

	private val binding by lazy { FragmentMovieBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<MovieViewModel> {
		parametersOf(arguments?.get(MOVIE))
	}

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)

		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}