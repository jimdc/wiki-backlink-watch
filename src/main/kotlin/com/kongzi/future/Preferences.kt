package com.kongzi.future

import android.content.Context
import android.support.v7.preference.PreferenceManager


class Preferences private constructor(context: Context) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    // @todo: SharedPreferences but also with PUT and APPLY to save new articles

    fun getStringPreference(key: String): String? {
        var value: String? = null
        if (preferences != null) {
            value = preferences!!.getString(key, null)
        }
        return value
    }

    fun putStringPreference(key: String, value: String): Boolean {
        if (preferences.contains(key)) {
            preferences.edit().putString(key, value).apply()
            return true
        }
        return false
    }

    companion object : SingletonHolder<Preferences, Context>(::Preferences)
}