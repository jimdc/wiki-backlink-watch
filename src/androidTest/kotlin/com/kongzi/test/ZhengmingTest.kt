package com.kongzi.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kongzi.view.ZhengmingActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Right click on package and do "run test in" and select emulator.
 */

@RunWith(AndroidJUnit4::class)
class ZhengmingTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(ZhengmingActivity::class.java)


    @Test
    fun saysHello() {
        onView(withText("Hello, Kotlin!")).check(matches(isDisplayed()))
    }
}
