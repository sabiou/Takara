package dev.farouk.takara.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.farouk.takara.DATABASE_NAME
import dev.farouk.takara.data.model.Candidate
import dev.farouk.takara.data.model.Event
import dev.farouk.takara.workmanager.CandidatesDataWorker
import dev.farouk.takara.workmanager.EventDataWorker

/**
 * Created by Farouk on 20/12/2020.
 */
@Database(entities = [Candidate::class, Event::class], version = 2, exportSchema = false)
abstract class TakaraDatabase : RoomDatabase() {
    abstract fun candidateDao(): CandidateDao
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var instance: TakaraDatabase? = null

        fun getInstance(context: Context): TakaraDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TakaraDatabase {
            return Room.databaseBuilder(context, TakaraDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val eventDataWorkRequest = OneTimeWorkRequestBuilder<EventDataWorker>().build()
                            val candidatesDataWorkRequest = OneTimeWorkRequestBuilder<CandidatesDataWorker>().build()
                            WorkManager.getInstance(context)
                                .beginWith(eventDataWorkRequest)
                                .then(candidatesDataWorkRequest)
                                .enqueue()
                        }
                    }
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}