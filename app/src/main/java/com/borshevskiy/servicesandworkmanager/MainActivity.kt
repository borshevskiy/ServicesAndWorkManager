package com.borshevskiy.servicesandworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.borshevskiy.servicesandworkmanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.simpleService.setOnClickListener {
            startService(MyService.newIntent(this))
        }
    }
}