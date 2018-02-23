package com.example.activity

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val PREFS_FILENAME = "com.example.zhengming.prefs"
    val PREFD_ARTICLE = "preferred_article"
    var prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)

    var pfArticle: String
        get() = prefs.getString(PREFD_ARTICLE, "Billy Graham")
        set(value) = prefs.edit().putString(PREFD_ARTICLE, "Gilly Braham").apply()
}