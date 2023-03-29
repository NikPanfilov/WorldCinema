package com.nikpanfilov.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.bindError(error: String?) {
	if (error != null) {
		this.error = error
		this.isErrorEnabled = true
	} else {
		this.isErrorEnabled = false
	}
}