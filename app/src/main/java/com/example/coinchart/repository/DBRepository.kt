package com.example.coinchart.repository

import com.example.coinchart.Application
import com.example.coinchart.db.CoinPriceDatabase
import com.example.coinchart.db.entity.InterestCoinEntity

class DBRepository {

    val context = Application.context()
    val db = CoinPriceDatabase.getDatabase(context)

    fun getAllInterestCoinData() = db.interestCoinDao().getAllData()

    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDao().insert(interestCoinEntity)

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDao().update(interestCoinEntity)

    fun getAllInterestSelectedCoinData() = db.interestCoinDao().getSelectedData()
}