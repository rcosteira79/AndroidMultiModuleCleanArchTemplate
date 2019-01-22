package com.rcosteira.template.presentation.main.menu

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.rcosteira.template.R
import com.rcosteira.template.testing.SingleFragmentActivity
import com.rcosteira.template.utils.createViewModelFactoryFor
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