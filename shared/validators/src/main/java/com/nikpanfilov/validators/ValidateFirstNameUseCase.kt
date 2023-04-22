package com.nikpanfilov.validators

import com.nikpanfilov.shared.validators.R

class ValidateFirstNameUseCase {

	operator fun invoke(name: String) = when {
		name.isBlank()        -> R.string.firstname_empty
		name.hasNonLetters()  -> R.string.firstname_incorrect_symbols
		!name.isNameCorrect() -> R.string.firstname_different_locals
		else                  -> null
	}

	private fun String.hasNonLetters(): Boolean {
		val regex = Regex("""^[а-яА-Яa-zA-Z]+$""")
		return regex.find(this) == null
	}

	private fun String.isNameCorrect(): Boolean {
		val regexRu = Regex("""^[а-яА-Я]+$""")
		val regexEn = Regex("""^[a-zA-Z]+$""")

		return regexRu.find(this) != null || regexEn.find(this) != null
	}
}