package com.nikpanfilov.validators

import com.nikpanfilov.shared.validators.R

class ValidateLastNameUseCase {

	operator fun invoke(name: String) = when {
		name.isBlank()        -> R.string.lastname_empty
		name.hasNonLetters()  -> R.string.lastname_incorrect_symbols
		!name.isNameCorrect() -> R.string.lastname_different_locals
		else                  -> null
	}

	private fun String.hasNonLetters(): Boolean {
		val regex = Regex("""^[а-яА-Яa-zA-Z]+$""")
		return regex.find(this) == null
	}

	private fun String.isNameCorrect(): Boolean {
		val regexRu = Regex("""^[а-яА-Я]?[а-яА-Я]*(-[а-яА-Я]?[а-яА-Я]*)*$""")
		val regexEn = Regex("""^[a-zA-Z]?[a-zA-Z]*(-[a-zA-Z]?[a-zA-Z]*)*$""")
		return regexRu.find(this) != null || regexEn.find(this) != null
	}
}