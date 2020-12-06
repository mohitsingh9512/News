package com.example.tnews.di.main

import android.content.Context
import com.example.tnews.network.MainApiInterface
import com.example.tnews.persistance.AppDatabase
import com.example.tnews.persistance.NewsDao
import com.example.tnews.persistance.SourcesDao
import com.example.tnews.ui.MainActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainActivityModule {

    @Provides
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }

    @Provides
    fun provideSourcesDao(db: AppDatabase): SourcesDao {
        return db.getSourcesDao()
    }

    @Provides
    fun provideNewsDao(db: AppDatabase): NewsDao {
        return db.getNewsDao()
    }

}