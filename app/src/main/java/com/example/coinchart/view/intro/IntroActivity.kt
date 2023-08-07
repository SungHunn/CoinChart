package com.example.coinchart.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.coinchart.MainActivity
import com.example.coinchart.R
import com.example.coinchart.databinding.ActivityIntroBinding
import timber.log.Timber

class IntroActivity : AppCompatActivity() {


    private lateinit var binding : ActivityIntroBinding
    private val viewModel : IntroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)

        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.checkFirtFlag()

        viewModel.first.observe(this) {
            if(it) {
                //접속한 적 있는 유저
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                //처음 접속하는 유저
                binding.animationView.visibility = View.INVISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        }


    }
}