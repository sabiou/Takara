package dev.farouk.takara.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.farouk.takara.data.model.Candidate
import kotlinx.coroutines.flow.Flow

/**
 * Created by Farouk on 20/12/2020.
 */
@Dao
interface CandidateDao {
    // get all candidates
    @Query("SELECT * FROM candidates ORDER BY name")
    fun getCandidates(): Flow<List<Candidate>>

    // insert all
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(candidates: List<Candidate>)
}