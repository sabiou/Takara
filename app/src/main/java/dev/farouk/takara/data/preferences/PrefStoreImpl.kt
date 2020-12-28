package dev.farouk.takara.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject

/**
 * Created by Farouk on 27/12/2020.
 */
class PrefStoreImpl @Inject constructor(@ApplicationContext context: Context) : PrefStore {

    private val dataStore = context.createDataStore(TAKARA_STORE_NAME)

    override fun isNightMode() = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[NIGHT_MODE_PREFS_KEY] ?: false }

    override suspend fun toggleNightMode() {
        dataStore.edit { prefs ->
            prefs[NIGHT_MODE_PREFS_KEY] = !(prefs[NIGHT_MODE_PREFS_KEY] ?: false)
        }
    }

    companion object {
        private const val TAKARA_STORE_NAME = "takara_data_store"
        private val NIGHT_MODE_PREFS_KEY = preferencesKey<Boolean>("dark_theme_enabled")
    }
}