package com.nikpanfilov.collections.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.collections.databinding.FragmentCollectionMoviesBinding
import com.nikpanfilov.collections.presentation.CollectionMoviesViewModel
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.shared.collections.domain.entity.Collection
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CollectionMoviesFragment : Fragment() {

	companion object {

		private const val COLLECTION = "collection"

		fun newInstance(collection: Collection) = CollectionMoviesFragment().apply {
			arguments = bundleOf(COLLECTION to collection)
		}
	}

	private val binding by lazy { FragmentCollectionMoviesBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<CollectionMoviesViewModel> {
		parametersOf(arguments?.get(COLLECTION))
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