package com.nikpanfilov.signin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.nikpanfilov.core.navigation.OnFragmentChangedListener
import com.nikpanfilov.signin.databinding.FragmentSignInBinding
import com.nikpanfilov.signin.presentation.SignInViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

	companion object {

		fun newInstance() = SignInFragment()
	}

	private val binding by lazy { FragmentSignInBinding.inflate(layoutInflater) }
	private val viewModel by viewModel<SignInViewModel>()

	private var listener: OnFragmentChangedListener? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
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