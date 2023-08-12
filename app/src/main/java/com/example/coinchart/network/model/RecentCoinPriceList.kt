package com.example.coinchart.network.model

import com.example.coinchart.dataModel.RecentPriceData

data class RecentCoinPriceList(

    val status : String,
    val data : List<RecentPriceData>
)
