package com.pasukanlangit.id.cleanarch_ktorclient.data.datasources.sharedpref

import android.content.SharedPreferences
import com.pasukanlangit.id.cleanarch_ktorclient.domain.model.Auth
import java.util.*

class SharedPref(
    private val sharedPref: SharedPreferences
) {
    fun getAuth(): Auth {
        val uuid = sharedPref.getString(KEY_UUID, null)
        val token = sharedPref.getString(KEY_ACCESS_TOKEN, null)
        return Auth(
            uuid = uuid,
            token = token
        )
    }

    fun setAuth(uuid: String, token: String) {
        return sharedPref
            .edit()
            .putString(KEY_ACCESS_TOKEN, token)
            .putString(KEY_UUID, uuid)
            .apply()
    }


    companion object {
        const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        const val KEY_UUID = "KEY_UUID"
    }
}