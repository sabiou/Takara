package dev.farouk.takara.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Farouk on 20/12/2020.
 */
@Entity(tableName = "events")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val date: String,
    val title: String
)