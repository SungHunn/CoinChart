package com.example.coinchart.network

import com.example.coinchart.network.model.CurrentPriceList
import com.example.coinchart.network.model.RecentCoinPriceList
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("public/ticker/ALL_KRW")
    suspend fun getCurrentCoinList() : CurrentPriceList

    @GET("public/transaction_history/{coin}_KRW")
    suspend fun getRecentCoinPrice(@Path("coin") coin : String) : RecentCoinPriceList
}