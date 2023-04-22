package com.nikpanfilov.profile.data.storage

import android.content.Context
import android.content.SharedPreferences
import com.nikpanfilov.core.network.token.data.storage.SharedPrefsDataStorage
import com.nikpanfilov.shared.profile.data.storage.UserIdDataStorageImpl

class LogOutDataStorageImpl(context: Context) : LogOutDataStorage {

	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
	}

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun logOut() {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(SharedPrefsDataStorage.ACCESS_TOKEN, null)
		e.putString(SharedPrefsDataStorage.REFRESH_TOKEN, null)
		e.putString(UserIdDataStorageImpl.USER_ID, null)

		e.apply()
	}
}