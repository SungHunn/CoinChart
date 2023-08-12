package com.example.coinchart.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coinchart.repository.DBRepository
import com.example.coinchart.repository.NetworkRepository
import timber.log.Timber

//최근 거래된 코인 내역 가져오는 workmanager
class RecentContractedWorkManager(val context : Context, workerParameters: WorkerParameters)
    : CoroutineWorker(context, workerParameters){

    private val dbRepository = DBRepository()
    private val networkRepository = NetworkRepository()

    override suspend fun doWork(): Result {

        Timber.d("manager")
        getAllInterestedSelectedCoinData()

        return Result.success()
    }

    suspend fun getAllInterestedSelectedCoinData(){
        val selectedCoinList = dbRepository.getAllInterestSelectedCoinData()

        for(coinData in selectedCoinList){


            val recentCoinPriceList = networkRepository.getInterestCoinPriceData(coinData.coin_name)

            Timber.d(recentCoinPriceList.toString())
        }
    }

}