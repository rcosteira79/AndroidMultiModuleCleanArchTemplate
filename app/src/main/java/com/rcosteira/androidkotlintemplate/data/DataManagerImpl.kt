package com.rcosteira.androidkotlintemplate.data

import com.rcosteira.androidkotlintemplate.data.api.ApiHelper
import  com.rcosteira.androidkotlintemplate.data.database.DatabaseHelper
import com.rcosteira.androidkotlintemplate.data.preferences.PreferencesHelper
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
        private val databaseHelper: DatabaseHelper,
        private val preferencesHelper: PreferencesHelper,
        private val apiHelper: ApiHelper
) : DataManager {

}