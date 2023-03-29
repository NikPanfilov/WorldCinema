package com.nikpanfilov.validators

class ValidatePasswordUseCase {

	companion object {

		const val MIN_PASSWORD_LENGTH = 8
	}

	operator fun invoke(password: String) = when {
		password.isBlank()                    -> R.string.password_empty
		!password.hasDigits()                 -> R.string.password_must_contain_digits
		!password.hasLowerCase()              -> R.string.password_must_contain_lower_case
		!password.hasUpperCase()              -> R.string.password_must_contain_upper_case
		password.length < MIN_PASSWORD_LENGTH -> R.string.password_small_length
		else                                  -> null
	}

	private fun String.hasDigits(): Boolean {
		val regex = Regex("""\d""")
		return regex.find(this) != null
	}

	private fun String.hasLowerCase(): Boolean {
		val regex = Regex("""[a-z]""")
		return regex.find(this) != null
	}

	private fun String.hasUpperCase(): Boolean {
		val regex = Regex("""[A-Z]""")
		return regex.find(this) != null
	}
}