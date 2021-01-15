package com.rma.marvelchallenge.core.platform

import android.content.Context
import android.content.SharedPreferences

class SessionManager constructor(context: Context, storedName: String?) {

    private val pref: SharedPreferences = context.getSharedPreferences(
        storedName,
        Context.MODE_PRIVATE
    )

    private val editor: SharedPreferences.Editor

    companion object {
        const val PREFERENCE_NAME = "session_data"
        private const val KEY_USER_UID = "userUid"
        private const val KEY_USER_EMAIL = "userEmail"
    }

    val uid: String
        get() = pref.getString(KEY_USER_UID, "") ?: ""

    val email: String
        get() = pref.getString(KEY_USER_EMAIL, "") ?: ""


    init {
        editor = pref.edit()
        editor.apply()
    }

    fun login(uid: String, email: String) {
        editor.putString(KEY_USER_UID, uid)
        editor.putString(KEY_USER_EMAIL, email)
        editor.commit()
    }
}