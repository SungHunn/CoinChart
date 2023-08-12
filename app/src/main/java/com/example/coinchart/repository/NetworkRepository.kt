package com.example.coinchart.repository

import com.example.coinchart.network.Api
import com.example.coinchart.network.RetrofitInstance

class NetworkRepository {

    private val client = RetrofitInstance.getInstance().create(Api::class.java)

    suspend fun getCurrentCoinList() = client.getCurrentCoinList()

    suspend fun getInterestCoinPriceData(coin :String) = client.getRecentCoinPrice(coin)
}