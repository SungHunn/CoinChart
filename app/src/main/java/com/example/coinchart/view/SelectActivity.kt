package com.example.coinchart.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.coinchart.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {

    var binding: ActivitySelectBinding? = null
    private val viewModel : SelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


        viewModel.getCurrentCoinList()
    }
}