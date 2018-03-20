package com.kongzi.dagger

import android.content.Context
import com.kongzi.WeKorrektor
import dagger.Module
import dagger.Provides

@Module
class AppModule{

    @Provides
    fun providesContext(application: WeKorrektor): Context {
        return application.applicationContext
    }
}