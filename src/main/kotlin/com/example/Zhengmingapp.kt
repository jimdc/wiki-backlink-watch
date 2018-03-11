package com.example

import android.app.Application
import android.util.Log
import android.widget.Toast
import com.example.activity.Prefs
import com.example.WikiApiService
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

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