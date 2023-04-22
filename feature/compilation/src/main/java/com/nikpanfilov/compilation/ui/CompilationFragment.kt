package com.nikpanfilov.compilation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.compilation.databinding.FragmentCompilationBinding
import com.nikpanfilov.compilation.presentation.CompilationViewModel
import com.nikpanfilov.compilation.ui.adapter.CardStackAdapter
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.shared.movie.domain.entity.Movie
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import org.koin.androidx.viewmodel.ext.android.viewModel

class CompilationFragment : Fragment() {
	companion object {

		fun newInstance() = CompilationFragment()
	}

	private val binding by lazy { FragmentCompilationBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<CompilationViewModel>()

	private var listener: OnFragmentChangedListener? = null

	private var currentMovie: Movie? = null
	private val cardStackAdapter = CardStackAdapter()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)
		binding.cardStackView.layoutManager = CardStackLayoutManager(
			requireContext(),
			CardStackListener(this::swipe, this::cardChange, this::checkCount)
		)

		binding.cardStackView.adapter = cardStackAdapter
		viewModel.moviesFlow.launch(viewLifecycleOwner.lifecycleScope) {
			if (it.isEmpty()) {
				hideCard()
			} else {
				showCard()
				cardStackAdapter.submitList(it)
				cardChange(0)
			}
		}

		binding.dislikeButton.setOnClickListener {
			if (currentMovie != null) {
				viewModel.dislike(currentMovie!!.movieId)
				binding.cardStackView.swipe()
			}
		}
		binding.addToFavouriteButton.setOnClickListener {
			if (currentMovie != null) {
				viewModel.toFavourite(currentMovie!!.movieId)
				binding.cardStackView.swipe()
			}
		}
		binding.playButton.setOnClickListener {
			if (currentMovie != null) {
				viewModel.navigateToMovie(currentMovie!!)
			}
		}

		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}

	private fun swipe(direction: Direction?) {
		if (direction == Direction.Left) {
			currentMovie?.let { viewModel.dislike(it.movieId) }
		}
		if (direction == Direction.Right) {
			currentMovie?.let { viewModel.toFavourite(it.movieId) }
		}
	}

	private fun cardChange(position: Int) {
		currentMovie = cardStackAdapter.currentList[position]
		binding.movieNameTextView.text = currentMovie!!.name
	}

	private fun checkCount(position: Int) {
		if (position == cardStackAdapter.currentList.size) {
			hideCard()
		} else {
			showCard()
		}
	}

	private fun hideCard() {
		binding.dislikeButton.visibility = View.GONE
		binding.playButton.visibility = View.GONE
		binding.addToFavouriteButton.visibility = View.GONE
		binding.movieNameTextView.visibility = View.GONE
		binding.cardStackView.visibility = View.INVISIBLE

		binding.noMoviesImageView.visibility = View.VISIBLE
		binding.noMoviesTextView.visibility = View.VISIBLE
	}

	private fun showCard() {
		binding.dislikeButton.visibility = View.VISIBLE
		binding.playButton.visibility = View.VISIBLE
		binding.addToFavouriteButton.visibility = View.VISIBLE
		binding.movieNameTextView.visibility = View.VISIBLE
		binding.cardStackView.visibility = View.VISIBLE

		binding.noMoviesImageView.visibility = View.GONE
		binding.noMoviesTextView.visibility = View.GONE
	}
}