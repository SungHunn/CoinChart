package com.example.coinchart.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinchart.dataModel.CurrentPrice
import com.example.coinchart.dataModel.CurrentPriceResult
import com.example.coinchart.repository.NetworkRepository
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class SelectViewModel : ViewModel() {

    private val netWorkRepository = NetworkRepository()
    private lateinit var currentPriceResultList : ArrayList<CurrentPriceResult>

    fun getCurrentCoinList() = viewModelScope.launch {

        val result = netWorkRepository.getCurrentCoinList()

        currentPriceResultList = ArrayList()

        for (coin in result.data){

            try {
                val gson = Gson()
                val gsonToJson = gson.toJson(result.data.get(coin.key))
                val gsonFromJson = gson.fromJson(gsonToJson, CurrentPrice::class.java)

                val currentPriceResult = CurrentPriceResult(coin.key, gsonFromJson)

                currentPriceResultList.add(currentPriceResult)
            }catch (e : Exception){
                Timber.d(e.toString())
            }

        }
    }
}