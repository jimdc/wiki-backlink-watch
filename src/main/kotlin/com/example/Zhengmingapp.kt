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

var disposable: Disposable? = null

class Zhengmingapp : Application() {
    companion object {
        var prefs: Prefs? = null
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }

    private fun beginSearch(searchString: String) {
        disposable = wikiApiServe.hitCountCheck("query", "json", "search", searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.v("Zhengmingapp", "${result.query.searchinfo.totalhits} result found") },
                        { error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }

    override fun onTerminate() {
        super.onTerminate()
        disposable?.dispose()
    }

}