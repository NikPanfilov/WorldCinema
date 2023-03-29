package com.nikpanfilov.validators

class ValidateEmailUseCase {

	operator fun invoke(email: String) = when {
		email.isEmpty()              -> R.string.email_empty
		!email.isNameCorrect()       -> R.string.email_incorrect_name
		!email.isDomainNameCorrect() -> R.string.email_incorrect_domain_name
		else                         -> null
	}

	private fun String.isNameCorrect(): Boolean {
		val regex = Regex("""^[a-z\d]+$""")
		return regex.find(this.substringBefore('@')) != null
	}

	private fun String.isDomainNameCorrect(): Boolean {
		val regex = Regex("""^[a-z\d]+\.[a-z\d]+$""")
		return regex.find(this.substringAfter('@')) != null
	}
}