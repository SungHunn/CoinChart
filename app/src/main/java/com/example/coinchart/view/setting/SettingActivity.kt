package com.example.coinchart.view.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coinchart.R
import com.example.coinchart.databinding.ActivitySelectBinding

import com.example.coinchart.databinding.ActivitySettingBinding
import com.example.coinchart.service.PriceForegroundService

class SettingActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startForeground.setOnClickListener {

            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "START"
            startService(intent)

        }

        binding.stopForeground.setOnClickListener {

            val intent = Intent(this, PriceForegroundService::class.java)
            intent.action = "STOP"
            startService(intent)
        }
    }
}