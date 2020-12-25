package dev.farouk.takara.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.farouk.takara.data.model.Event
import kotlinx.coroutines.flow.Flow

/**
 * Created by Farouk on 25/12/2020.
 */
@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getEvents(): Flow<List<Event>>

    // insert all
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<Event>)
}