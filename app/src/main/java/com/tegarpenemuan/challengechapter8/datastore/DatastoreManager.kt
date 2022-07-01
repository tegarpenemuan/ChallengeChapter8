package com.tegarpenemuan.challengechapter8.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.tegarpenemuan.challengechapter8.common.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import java.io.IOException
import javax.inject.Inject

/**
 * com.tegarpenemuan.secondhandecomerce.datastore
 *
 * Created by Tegar Penemuan on 24/06/2022.
 * https://github.com/tegarpenemuanr3
 *
 */

fun Context.pref(): DatastoreManager {
    return DatastoreManager(this)
}

class DatastoreManager @Inject constructor(private val context: Context) {
    companion object {
        val Context.dataStoreAuth: DataStore<Preferences> by preferencesDataStore(
            name = Constant.PrefDataSore.PREF_NAME,
            produceMigrations = Companion::sharedPreferencesMigration
        )

        private fun sharedPreferencesMigration(context: Context) =
            listOf(SharedPreferencesMigration(context, Constant.PrefDataSore.PREF_NAME))
    }

    fun getIsLogin(): Flow<Boolean> {
        return context.dataStoreAuth.data.map { preferences ->
            preferences[Constant.PrefDataSore.IS_LOGIN] == false
        }
    }

    suspend fun setIsLogin(value: Boolean) {
        context.dataStoreAuth.edit { preferences ->
            preferences[Constant.PrefDataSore.IS_LOGIN] = value
        }
    }

    fun getUsername(): Flow<String> {
        return context.dataStoreAuth.data.map { preferences ->
            preferences[Constant.PrefDataSore.USERNAME].orEmpty()
        }
    }

    suspend fun setUsername(value: String) {
        context.dataStoreAuth.edit { preferences ->
            preferences[Constant.PrefDataSore.USERNAME] = value
        }
    }

    fun getId(): Flow<String> {
        return context.dataStoreAuth.data.map { preferences ->
            preferences[Constant.PrefDataSore.ID].orEmpty()
        }
    }

    suspend fun setId(value: String) {
        context.dataStoreAuth.edit { preferences ->
            preferences[Constant.PrefDataSore.ID] = value
        }
    }

    fun getEmail(): Flow<String> {
        return context.dataStoreAuth.data.map { preferences ->
            preferences[Constant.PrefDataSore.EMAIL].orEmpty()
        }
    }

    suspend fun setEmail(value: String) {
        context.dataStoreAuth.edit { preferences ->
            preferences[Constant.PrefDataSore.EMAIL] = value
        }
    }

    fun getPrefId() = runBlocking { context.dataStoreAuth.getValue(Constant.PrefDataSore.ID, "").firstOrNull() }
}

fun <T> DataStore<Preferences>.getValue(
    key: Preferences.Key<T>,
    defaultValue: T
) = this.data
    .catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[key] ?: defaultValue
    }
