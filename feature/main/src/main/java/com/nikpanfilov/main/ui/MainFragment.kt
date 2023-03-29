package com.nikpanfilov.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.main.databinding.FragmentMainBinding
import com.nikpanfilov.main.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

	companion object {

		fun newInstance() = MainFragment()
	}

	private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<MainViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)

		return binding.root
	}
}