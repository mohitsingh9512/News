package com.example.tnews.di

import com.example.tnews.di.main.MainActivityModule
import com.example.tnews.di.main.MainFragmentBuildersModule
import com.example.tnews.di.main.MainRepositoriesModule
import com.example.tnews.di.main.MainViewModelsModule
import com.example.tnews.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class,
            MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainRepositoriesModule::class]
    )
    abstract fun contributesMainActivity(): MainActivity

}