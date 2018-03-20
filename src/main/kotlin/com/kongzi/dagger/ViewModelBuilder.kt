package com.kongzi.dagger

import android.arch.lifecycle.ViewModelProvider
import com.kongzi.dagger.DaggerAwareViewModelFactory
import dagger.Binds
import dagger.Module

@Module
internal abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DaggerAwareViewModelFactory):
            ViewModelProvider.Factory
}