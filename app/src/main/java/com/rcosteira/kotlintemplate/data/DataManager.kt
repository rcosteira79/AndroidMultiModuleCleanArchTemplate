package com.rcosteira.kotlintemplate.data

import com.rcosteira.kotlintemplate.data.api.ApiHelper
import com.rcosteira.kotlintemplate.data.database.DatabaseHelper
import com.rcosteira.kotlintemplate.data.preferences.PreferencesHelper

/**
 * Repository class meant to be the only access point to all other data related classes
 */
interface DataManager: ApiHelper, DatabaseHelper, PreferencesHelper {
}