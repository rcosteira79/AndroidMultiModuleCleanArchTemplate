package com.rcosteira.kotlintemplate.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.rcosteira.kotlintemplate.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    val rule = object : ActivityTestRule<MainActivity>(MainActivity::class.java, false, false) {
        // TODO override intent if needed
    }

    @Test
    fun shouldNavigateToMainMenu() {
        rule.launchActivity(null)
        onView(withId(R.id.tvHello)).check(matches(isDisplayed()))
    }
}