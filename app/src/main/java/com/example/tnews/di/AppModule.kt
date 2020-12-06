package com.example.tnews.di

import android.app.Application
import android.content.Context
import com.example.tnews.utlis.ActivityHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class,
    RoomModule::class
])
class AppModule {

    private var mApplication: Application? = null

    @Provides
    @Singleton
    fun providesApplicationContext(): Context? {
        return mApplication?.applicationContext
    }

    @Provides
    fun providesActivityHelper() = ActivityHelper()
}