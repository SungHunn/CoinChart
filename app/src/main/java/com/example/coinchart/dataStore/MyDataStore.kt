package com.example.coinchart.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.coinchart.Application

class MyDataStore {

    private val context = Application.context()

    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("user_pref")
    }

    private val mDataStore : DataStore<Preferences> = context.dataStore
    private val FIRST_FLAG = booleanPreferencesKey("FIRST_FLAG")

    // 접속 여부 (true/false)
    suspend fun setupFirstData(){
        mDataStore.edit { preferences ->
            preferences[FIRST_FLAG] = true
        }
    }

    suspend fun getFirstData() : Boolean {

        var currentValue = false

        mDataStore.edit { prefernces ->
            currentValue = prefernces[FIRST_FLAG] ?: false
        }

        return currentValue
    }
}