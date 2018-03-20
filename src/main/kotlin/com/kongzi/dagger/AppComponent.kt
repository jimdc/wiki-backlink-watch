package com.kongzi.dagger

import com.kongzi.WeKorrektor
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModelBuilder::class,
            MainActivityModule::class])
interface AppComponent : AndroidInjector<WeKorrektor> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<WeKorrektor>()
}