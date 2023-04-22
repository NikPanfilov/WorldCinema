package com.nikpanfilov.collections.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.collections.databinding.FragmentCollectionsBinding
import com.nikpanfilov.collections.presentation.CollectionsViewModel
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionsFragment : Fragment() {

	companion object {

		fun newInstance() = CollectionsFragment()
	}

	private val binding by lazy { FragmentCollectionsBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<CollectionsViewModel>()

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)
		viewModel.getCollections()

		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}