package com.example.tnews.di

import android.app.Application
import com.example.tnews.BaseApplication
import com.example.tnews.di.viewmodelfactory.ViewModelFactoryModule
import com.example.tnews.utlis.ActivityHelper
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    val activityHelper : ActivityHelper

}