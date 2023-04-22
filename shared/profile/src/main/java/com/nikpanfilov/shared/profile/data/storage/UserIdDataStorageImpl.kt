package com.nikpanfilov.shared.profile.data.storage

import android.content.Context
import android.content.SharedPreferences

class UserIdDataStorageImpl(context: Context) : UserIdDataStorage {

	companion object {

		const val SHARED_PREFERENCES_FILENAME = "myPrefs"
		const val USER_ID = "user_id"
	}

	private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_FILENAME, Context.MODE_PRIVATE)

	override fun saveUserId(id: String) {
		val e: SharedPreferences.Editor = sharedPreferences.edit()

		e.putString(USER_ID, id)

		e.apply()
	}

	override fun loadUserId(): String =
		sharedPreferences.getString(USER_ID, "") ?: ""
}