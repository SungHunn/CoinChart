package com.example.coinchart.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.coinchart.db.dao.InterestCoinDao
import com.example.coinchart.db.dao.SelectedCoinPriceDAO
import com.example.coinchart.db.entity.DateConverters
import com.example.coinchart.db.entity.InterestCoinEntity
import com.example.coinchart.db.entity.SelectedCoinPriceEntity

@Database(entities = [InterestCoinEntity::class , SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DateConverters::class)
abstract class CoinPriceDatabase : RoomDatabase() {

    abstract fun interestCoinDao() : InterestCoinDao
    abstract fun selectedCoinDao() : SelectedCoinPriceDAO

    companion object {

        @Volatile
        private var INSTANCE : CoinPriceDatabase? = null

        fun getDatabase(
            context : Context
        ) : CoinPriceDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}