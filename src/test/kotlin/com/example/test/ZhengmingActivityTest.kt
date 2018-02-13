package com.example.test

import com.example.BuildConfig
import com.example.activity.ZhengmingActivity

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config

import org.junit.Assert.assertTrue
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(25))
class ZhengmingActivityTest {

    @Test
    fun testSomething() {
        assertTrue(Robolectric.setupActivity(ZhengmingActivity::class.java) != null)
    }
}
