package com.kongzi

import android.app.Application
import android.support.v7.preference.PreferenceManager
import com.kongzi.model.DataModel
import com.kongzi.model.IDataModel
import com.kongzi.model.SchedulerProvider
import com.kongzi.model.WikiApiService
import com.kongzi.viewmodel.MainViewModel

val wikiApiServe by lazy { WikiApiService.create() }

class Zhengmingapp : Application() {

    override fun onCreate() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
        super.onCreate()
    }

}