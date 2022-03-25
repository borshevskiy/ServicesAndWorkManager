package com.borshevskiy.servicesandworkmanager

import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(context: Context, private val workerParameters: WorkerParameters): Worker(context, workerParameters) {

    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i")
        }
        return Result.success()
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyIntentService: $message")
    }

    companion object {
        const val WORK_NAME = "work_name"
        private const val PAGE = "page"

        fun makeRequest(page: Int): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<MyWorker>().setInputData(workDataOf(PAGE to page)).
            setConstraints(makeConstraints()).build()
        }

        private fun makeConstraints() = Constraints.Builder().setRequiresCharging(true).build()
    }
}