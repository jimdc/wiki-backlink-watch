package com.kongzi

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class WeKorrektor : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}