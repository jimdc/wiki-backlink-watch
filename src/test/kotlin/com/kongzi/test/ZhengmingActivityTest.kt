package com.kongzi.test

import com.kongzi.BuildConfig
import com.kongzi.view.MainActivity

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.annotation.Config

import org.junit.Assert.assertTrue
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [25])
class ZhengmingActivityTest {

    @Test
    fun testSomething() {
        assertTrue(Robolectric.setupActivity(MainActivity::class.java) != null)
    }
}
