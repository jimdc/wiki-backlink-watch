package com.kongzi.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import io.reactivex.schedulers.*
import android.widget.EditText
import android.widget.Toast
import com.kongzi.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import android.support.v7.app.AppCompatActivity

var disposable: Disposable? = null

class ZhengmingActivity : AppCompatActivity() {

    lateinit var editTextPref: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pfArticle = prefs.pfArticle
        setContentView(R.layout.zhengming)

        editTextPref = findViewById<EditText>(R.id.pianhao)
        editTextPref.setText(pfArticle)

        lateinit var fragment: RecyclerViewFragment
        val adapter: BacklinkAdapter by lazy { fragment.mAdapter }

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            fragment = RecyclerViewFragment()
            transaction.replace(R.id.backlink_content_fragment, fragment)
            transaction.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        prefs.pfArticle = editTextPref.text.toString()
    }
}
