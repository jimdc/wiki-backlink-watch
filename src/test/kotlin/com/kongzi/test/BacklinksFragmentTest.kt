package com.kongzi.test

import com.kongzi.BuildConfig
import com.kongzi.past.BacklinksFragment

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment
import android.widget.Spinner
import org.junit.Assert.*
import com.kongzi.R


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [25])
class BacklinksFragmentTest {

    @Test
    fun testThatSpinnerHasLoaded() {
        val fragment = BacklinksFragment()
        startFragment(fragment)
        assertNotNull(fragment)
        val view = fragment.getView()
        assertNotNull(view)
        val spinner = view?.findViewById(R.id.articles) as Spinner
        assertNotNull(spinner)
    }
}
