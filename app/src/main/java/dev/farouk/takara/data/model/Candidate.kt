package dev.farouk.takara.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Farouk on 20/12/2020.
 */
@Entity(tableName = "candidates")
data class Candidate(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val party: String,
    val imageUrl: String? = "",
    val votersTally: Int = 0,
    val percentage: String = ""
)