package com.example

import android.app.Application
import com.example.activity.Prefs

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