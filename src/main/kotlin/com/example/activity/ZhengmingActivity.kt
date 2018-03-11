package com.example.activity

import android.app.Activity
import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import io.reactivex.schedulers.*
import android.widget.EditText
import android.widget.Toast
import com.example.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

var disposable: Disposable? = null

class ZhengmingActivity : Activity() {

    lateinit var editTextPref: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pfArticle = prefs.pfArticle
        setContentView(R.layout.zhengming)

        editTextPref = findViewById<EditText>(R.id.pianhao)
        editTextPref.setText(pfArticle)
        beginSearch("Donald Trump")
    }


    private fun beginSearch(searchString: String) {
        disposable = wikiApiServe.backlinks(bltitle = searchString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            result.query.backlinks.forEach {
                                Log.v ("Zhengmingapp", it.title)
                            }
                        },
                        { error -> Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show() }
                )
    }

    override fun onDestroy() {
        super.onDestroy()

        prefs.pfArticle = editTextPref.text.toString()
    }
}
