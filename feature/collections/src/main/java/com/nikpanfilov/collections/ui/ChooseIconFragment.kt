package com.nikpanfilov.collections.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nikpanfilov.collections.databinding.FragmentChooseIconBinding
import com.nikpanfilov.collections.presentation.ChooseIconViewModel
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.shared.collections.domain.entity.Collection
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChooseIconFragment : Fragment() {

	companion object {

		fun newInstance(collection: Collection) = ChooseIconFragment().apply {
			arguments = bundleOf(EditCollectionFragment.COLLECTION to collection)
		}
	}

	private val binding by lazy { FragmentChooseIconBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<ChooseIconViewModel>{
		parametersOf(arguments?.get(EditCollectionFragment.COLLECTION))
	}

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		binding.bindData(viewModel)
		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}