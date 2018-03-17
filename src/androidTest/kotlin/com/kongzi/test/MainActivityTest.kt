package com.kongzi.test

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.kongzi.viewmodel.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun saysHello() {
        //onView(withText("Hello, Kotlin!")).check(matches(isDisplayed()))
    }
}
