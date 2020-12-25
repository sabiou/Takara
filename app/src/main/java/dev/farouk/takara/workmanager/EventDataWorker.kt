package dev.farouk.takara.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dev.farouk.takara.CANDIDATES_DATA_FILENAME
import dev.farouk.takara.EVENTS_DATA_FILENAME
import dev.farouk.takara.data.db.TakaraDatabase
import dev.farouk.takara.data.model.Candidate
import dev.farouk.takara.data.model.Event
import kotlinx.coroutines.coroutineScope

/**
 * Created by Farouk on 25/12/2020.
 */
class EventDataWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(EVENTS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val event = object : TypeToken<List<Event>>() {}.type
                    val eventsList: List<Event> = Gson().fromJson(jsonReader, event)

                    // insert all in the database
                    val db = TakaraDatabase.getInstance(applicationContext)
                    db.eventDao().insertAll(eventsList)
                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error inserting data into database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "DatabaseWorker"
    }
}