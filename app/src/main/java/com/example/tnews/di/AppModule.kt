package com.example.tnews.di

import com.example.tnews.utlis.ActivityHelper
import dagger.Module
import dagger.Provides

@Module(includes = [RetrofitModule::class,
    RoomModule::class
])
class AppModule {

    @Provides
    fun providesActivityHelper() = ActivityHelper()
}