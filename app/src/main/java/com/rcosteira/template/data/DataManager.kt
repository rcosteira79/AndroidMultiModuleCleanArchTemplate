package com.rcosteira.template.data

import com.rcosteira.template.data.api.ApiHelper
import com.rcosteira.template.data.database.DatabaseHelper
import com.rcosteira.template.data.preferences.PreferencesHelper

/**
 * Repository class meant to be the only access point to all other data related classes
 */
interface DataManager: ApiHelper, DatabaseHelper, PreferencesHelper {
}