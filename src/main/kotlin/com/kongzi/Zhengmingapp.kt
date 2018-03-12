package com.kongzi

import android.app.Application
import android.support.v7.preference.PreferenceManager
import com.kongzi.model.WikiApiService

val wikiApiServe by lazy { WikiApiService.create() }

class Zhengmingapp : Application() {

    override fun onCreate() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        super.onCreate()
    }

}