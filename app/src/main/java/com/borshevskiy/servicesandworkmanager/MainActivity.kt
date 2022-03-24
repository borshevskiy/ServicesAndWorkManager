package com.borshevskiy.servicesandworkmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.borshevskiy.servicesandworkmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            simpleService.setOnClickListener {
                startService(MyService.newIntent(this@MainActivity, 25))
            }
            foregroundService.setOnClickListener {
                ContextCompat.startForegroundService(this@MainActivity, MyForegroundService.newIntent(this@MainActivity))
            }
        }
    }
}