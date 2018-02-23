package com.example.activity

import android.app.Activity
import android.os.Bundle
import android.content.SharedPreferences
import com.example.prefs
import com.example.Zhengmingapp
import com.example.R
import android.widget.EditText

class ZhengmingActivity : Activity() {

    lateinit var editTextPref: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pfArticle = prefs.pfArticle
        setContentView(R.layout.zhengming)

        editTextPref = findViewById<EditText>(R.id.pianhao)
        editTextPref.setText(pfArticle)
    }

    override fun onDestroy() {
        super.onDestroy()

        prefs.pfArticle = editTextPref.text.toString()
    }
}
