package com.rcosteira.androidkotlintemplate.data

import com.rcosteira.androidkotlintemplate.data.api.ApiHelper
import com.rcosteira.androidkotlintemplate.data.database.DatabaseHelper
import com.rcosteira.androidkotlintemplate.data.preferences.PreferencesHelper

/**
 * Repository class meant to be the only access point to all other data related classes
 */
interface DataManager: ApiHelper, DatabaseHelper, PreferencesHelper {
}