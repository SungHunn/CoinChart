package com.example.coinchart.repository

import com.example.coinchart.Application
import com.example.coinchart.db.CoinPriceDatabase
import com.example.coinchart.db.entity.InterestCoinEntity
import com.example.coinchart.db.entity.SelectedCoinPriceEntity

class DBRepository {

    val context = Application.context()
    val db = CoinPriceDatabase.getDatabase(context)

    fun getAllInterestCoinData() = db.interestCoinDao().getAllData()

    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDao().insert(interestCoinEntity)

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDao().update(interestCoinEntity)

    fun getAllInterestSelectedCoinData() = db.interestCoinDao().getSelectedData()


    //CoinPrice
    fun getAllCoinPriceData() = db.selectedCoinDao().getAllData()

    fun insertCoinPriceData(selectedCoinPriceEntity: SelectedCoinPriceEntity)
    = db.selectedCoinDao().insert(selectedCoinPriceEntity)

    fun getOneSelectedCoinData(coinName : String) = db.selectedCoinDao().getOneCoinData(coinName)
}