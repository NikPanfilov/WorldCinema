package com.nikpanfilov.signup.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.signup.databinding.FragmentSignUpBinding
import com.nikpanfilov.signup.presentation.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

	companion object {

		fun newInstance() = SignUpFragment()
	}

	private val binding by lazy { FragmentSignUpBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<SignUpViewModel>()

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		listener?.onFragmentChanged(this)
		this.bindData(binding, viewModel, viewLifecycleOwner.lifecycleScope)
		return binding.root
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)
		if (context is OnFragmentChangedListener) {
			listener = context
		}
	}
}