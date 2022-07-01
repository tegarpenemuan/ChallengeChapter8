package com.tegarpenemuan.challengechapter8.common

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object Constant {

    object ImageUrl {
        const val IMAGE_URL = "https://tegarpenemuan.xyz/storage/user/"
    }

    object PrefDataSore {
        const val PREF_NAME = "MyDatastore"
        val IS_LOGIN = booleanPreferencesKey("IS_LOGIN")
        val ID = stringPreferencesKey("ID")
        val USERNAME = stringPreferencesKey("USERNAME")
        val PASSWORD = stringPreferencesKey("PASSWORD")
        val EMAIL = stringPreferencesKey("EMAIL")
    }
}