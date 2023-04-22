package com.nikpanfilov.episode.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.core.navigation.holders.EpisodeHolder
import com.nikpanfilov.episode.databinding.FragmentEpisodeBinding
import com.nikpanfilov.episode.presentation.EpisodeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EpisodeFragment : Fragment() {

	companion object {

		private const val EPISODE = "episode"

		fun newInstance(episode: EpisodeHolder) = EpisodeFragment().apply {
			arguments = bundleOf(EPISODE to episode)
		}
	}

	private val binding by lazy { FragmentEpisodeBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<EpisodeViewModel> {
		parametersOf(arguments?.get(EPISODE))
	}

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		binding.bindData(viewModel)

		requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
			viewModel.navigateBack(binding.playerView.player.getTime())
		}

		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}