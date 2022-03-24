package com.borshevskiy.servicesandworkmanager

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.app.job.JobWorkItem
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.borshevskiy.servicesandworkmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            simpleService.setOnClickListener {
                stopService(MyForegroundService.newIntent(this@MainActivity))
                startService(MyService.newIntent(this@MainActivity, 25))
            }
            foregroundService.setOnClickListener {
                ContextCompat.startForegroundService(
                    this@MainActivity,
                    MyForegroundService.newIntent(this@MainActivity)
                )
            }
            intentService.setOnClickListener {
                ContextCompat.startForegroundService(
                    this@MainActivity,
                    MyIntentService.newIntent(this@MainActivity)
                )
            }
            jobScheduler.setOnClickListener {
                val componentName = ComponentName(this@MainActivity, MyJobService::class.java)
                val jobInfo =
                    JobInfo.Builder(MyJobService.JOB_ID, componentName).setRequiresCharging(true)
                        .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED).build()

                val jobScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    jobScheduler.enqueue(jobInfo, JobWorkItem(MyJobService.newIntent(page++)))
                } else {
                    startService(MySchedulerIntentService.newIntent(this@MainActivity,page++))
                }
            }
        }
    }
}