package com.example.coinchart.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.coinchart.background.RecentContractedWorkManager
import com.example.coinchart.view.main.MainActivity
import com.example.coinchart.databinding.ActivitySelectBinding
import com.example.coinchart.view.adapter.SelectRVAdapter
import timber.log.Timber
import java.util.concurrent.TimeUnit

class SelectActivity : AppCompatActivity() {

    lateinit var binding: ActivitySelectBinding
    private val viewModel: SelectViewModel by viewModels()

    private lateinit var selectRVAdapter: SelectRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel.getCurrentCoinList()
        viewModel.currentPriceResult.observe(this) {

            selectRVAdapter = SelectRVAdapter(this, it)

            binding.coinListRV.adapter = selectRVAdapter
            binding.coinListRV.layoutManager = LinearLayoutManager(this)

        }




        binding.laterTextArea.setOnClickListener {

            viewModel.setUpFirstFlag()
            viewModel.saveSelectedCoinList(selectRVAdapter.selectedCoinList)



        }

        viewModel.save.observe(this){
            if (it.equals("done")){

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                saveInterestCoinDataPeriodic()
            }
        }
    }

    private fun saveInterestCoinDataPeriodic(){

        val myWork = PeriodicWorkRequest.Builder(
            RecentContractedWorkManager::class.java,
            15,
            TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "RecentContractedWorkManager",
            ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )
    }

}