package com.nikpanfilov.worldcinema.di

import com.nikpanfilov.validators.ValidateEmailUseCase
import com.nikpanfilov.validators.ValidateFirstNameUseCase
import com.nikpanfilov.validators.ValidateLastNameUseCase
import com.nikpanfilov.validators.ValidatePasswordUseCase
import org.koin.dsl.module

val validatorsModule = module {
	single { ValidateEmailUseCase() }
	single { ValidateFirstNameUseCase() }
	single { ValidateLastNameUseCase() }
	single { ValidatePasswordUseCase() }
}