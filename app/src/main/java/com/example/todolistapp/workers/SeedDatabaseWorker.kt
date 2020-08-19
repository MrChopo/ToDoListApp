package com.example.todolistapp.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.todolistapp.data.AppDatabase
import com.example.todolistapp.utilities.TASK_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import com.example.todolistapp.data.Task

class SeedDatabaseWorker (
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(TASK_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val taskType = object : TypeToken<List<Task>>() {}.type
                    val taskList: List<Task> = Gson().fromJson(jsonReader, taskType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.taskDao().insertAll(taskList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}