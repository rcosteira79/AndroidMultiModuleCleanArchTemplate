package com.rcosteira.kotlintemplate.data

import com.rcosteira.kotlintemplate.data.api.ApiHelper
import  com.rcosteira.kotlintemplate.data.database.DatabaseHelper
import com.rcosteira.kotlintemplate.data.preferences.PreferencesHelper
import javax.inject.Inject

class DataManagerImpl @Inject constructor(private val databaseHelper: DatabaseHelper,
                                          private val preferencesHelper: PreferencesHelper,
                                          private val apiHelper: ApiHelper) : DataManager {

}