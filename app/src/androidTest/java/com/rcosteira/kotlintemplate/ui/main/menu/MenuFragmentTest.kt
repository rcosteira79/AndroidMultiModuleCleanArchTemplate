package com.rcosteira.kotlintemplate.ui.main.menu

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.rcosteira.kotlintemplate.R
import com.rcosteira.kotlintemplate.testing.SingleFragmentActivity
import com.rcosteira.kotlintemplate.utils.createViewModelFactoryFor
import kotlinx.android.synthetic.main.fragment_menu.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuFragmentTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(SingleFragmentActivity::class.java, true, true)

    lateinit var fragment: MenuFragment
    lateinit var viewModel: MenuViewModel

    @Before
    fun setup() {
        fragment = MenuFragment()
        viewModel = mock()

        fragment.viewModelFactory = createViewModelFactoryFor(viewModel)

        activityRule.activity.setFragment(fragment)
    }

    @Test
    fun shouldDisplayHelloText() {
        onView(withId(R.id.tvHello)).check(matches(isDisplayed()))

    }
}