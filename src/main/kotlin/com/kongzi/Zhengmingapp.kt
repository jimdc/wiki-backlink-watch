package com.kongzi

import android.app.Application
import com.kongzi.model.WikiApiService
import com.kongzi.model.Prefs

val wikiApiServe by lazy { WikiApiService.create() }
val prefs: Prefs by lazy { Zhengmingapp.prefs!! }

class Zhengmingapp : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }

}