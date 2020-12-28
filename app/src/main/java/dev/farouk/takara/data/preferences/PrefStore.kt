package dev.farouk.takara.data.preferences

import kotlinx.coroutines.flow.Flow

/**
 * Created by Farouk on 27/12/2020.
 */
interface PrefStore {
    fun isNightMode(): Flow<Boolean>

    suspend fun toggleNightMode()
}