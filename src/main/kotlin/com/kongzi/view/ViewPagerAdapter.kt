package com.kongzi.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

internal class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
    private val titles = arrayOf("Backlinks", "Context", "Replace", "Social")

    override fun getItem(position: Int) = DefaultTabFragment.getInstance(position)
    override fun getCount() = titles.size
    override fun getPageTitle(position: Int) = titles[position]
}