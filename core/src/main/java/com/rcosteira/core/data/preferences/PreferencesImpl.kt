package com.rcosteira.core.data.preferences

import android.content.Context
import com.rcosteira.core.data.AppConstants
import javax.inject.Inject

class PreferencesImpl @Inject constructor(context: Context): Preferences {

    private val preferences = context.getSharedPreferences(
            AppConstants.PREFERENCES_NAME, Context.MODE_PRIVATE)
}