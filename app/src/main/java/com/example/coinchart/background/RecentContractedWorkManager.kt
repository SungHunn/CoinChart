package com.example.coinchart.background

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.coinchart.db.entity.SelectedCoinPriceEntity
import com.example.coinchart.network.model.RecentCoinPriceList
import com.example.coinchart.repository.DBRepository
import com.example.coinchart.repository.NetworkRepository
import timber.log.Timber
import java.util.Calendar
import java.util.Date

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

        val timeStamp = Calendar.getInstance().time

        for(coinData in selectedCoinList){


            val recentCoinPriceList = networkRepository.getInterestCoinPriceData(coinData.coin_name)

            Timber.d(recentCoinPriceList.toString())

            saveSelectedCoinPrice(
                coinData.coin_name,
                recentCoinPriceList,
                timeStamp
            )
        }
    }

    fun saveSelectedCoinPrice(
        coinName :String,
        recentCoinPriceList: RecentCoinPriceList,
        timeStamp : Date
    ){

        val selectedCoinPriceEntity = SelectedCoinPriceEntity(
            0,
            coinName,
            recentCoinPriceList.data[0].transaction_date,
            recentCoinPriceList.data[0].type,
            recentCoinPriceList.data[0].units_traded,
            recentCoinPriceList.data[0].price,
            recentCoinPriceList.data[0].total,
            timeStamp

        )

        dbRepository.insertCoinPriceData(selectedCoinPriceEntity)
    }
}