package com.example.tnews.di.main

import com.example.tnews.ui.news.NewsRepository
import com.example.tnews.ui.news.NewsRepositoryImpl
import com.example.tnews.ui.sources.SourcesRepository
import com.example.tnews.ui.sources.SourcesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MainRepositoriesModule {

    @Binds
    abstract fun bindsSourcesRepository(sourcesRepositoryImpl: SourcesRepositoryImpl): SourcesRepository

    @Binds
    abstract fun bindsNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}