package com.nikpanfilov.signup.ui

import androidx.lifecycle.LifecycleCoroutineScope
import com.nikpanfilov.extensions.bindError
import com.nikpanfilov.extensions.bindText
import com.nikpanfilov.extensions.launch
import com.nikpanfilov.signup.databinding.FragmentSignUpBinding
import com.nikpanfilov.signup.presentation.SignUpSendState
import com.nikpanfilov.signup.presentation.SignUpState
import com.nikpanfilov.signup.presentation.SignUpViewModel

internal fun SignUpFragment.bindData(binding: FragmentSignUpBinding, viewModel: SignUpViewModel, scope: LifecycleCoroutineScope) {
	with(binding) {
		buttonSignUp.setOnClickListener { viewModel.signUp() }
		buttonToSignIn.setOnClickListener { viewModel.navigateToSignIn() }

		firstNameEditText.bindText(scope, viewModel.firstNameFlow)
		lastNameEditText.bindText(scope, viewModel.lastNameFlow)
		emailEditText.bindText(scope, viewModel.emailFlow)
		passwordEditText.bindText(scope, viewModel.passwordFlow)
		passwordRepeatEditText.bindText(scope, viewModel.passwordRepeatFlow)

		with(viewModel) {
			firstNameFlow.launch(scope) {
				firstNameInputLayout.bindError(getFirstNameError()?.let { getString(it) })
			}
			lastNameFlow.launch(scope) {
				lastNameInputLayout.bindError(getLastNameError()?.let { getString(it) })
			}
			emailFlow.launch(scope) {
				emailInputLayout.bindError(getEmailError()?.let { getString(it) })
			}
			passwordFlow.launch(scope) {
				passwordInputLayout.bindError(getPasswordError()?.let { getString(it) })
			}
			passwordRepeatFlow.launch(scope) {
				passwordRepeatInputLayout.bindError(getPasswordRepeatError()?.let { getString(it) })
			}

			stateFlow.launch(scope) {
				if (it is SignUpState.Content && it.sendState is SignUpSendState.Success) {
				    navigateToMain()
				}
			}
		}
	}
}
