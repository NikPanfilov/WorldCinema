package com.nikpanfilov.signin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.signin.databinding.FragmentSignInBinding
import com.nikpanfilov.signin.presentation.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

	companion object {

		fun newInstance() = SignInFragment()
	}

	private val binding by lazy { FragmentSignInBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<SignInViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding.bindData(viewModel, viewLifecycleOwner.lifecycleScope)
		return binding.root
	}
}