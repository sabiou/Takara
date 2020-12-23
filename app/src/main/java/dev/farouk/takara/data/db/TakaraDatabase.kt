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
import dev.farouk.takara.workmanager.DbWorker

/**
 * Created by Farouk on 20/12/2020.
 */
@Database(entities = [Candidate::class], version = 1, exportSchema = false)
abstract class TakaraDatabase : RoomDatabase() {
    abstract fun candidateDao(): CandidateDao

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
                            val request = OneTimeWorkRequestBuilder<DbWorker>().build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}