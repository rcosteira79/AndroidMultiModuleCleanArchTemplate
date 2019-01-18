package com.rcosteira.template.data

import com.rcosteira.template.data.api.ApiHelper
import  com.rcosteira.template.data.database.DatabaseHelper
import com.rcosteira.template.data.preferences.PreferencesHelper
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
        private val databaseHelper: DatabaseHelper,
        private val preferencesHelper: PreferencesHelper,
        private val apiHelper: ApiHelper
) : DataManager {

}