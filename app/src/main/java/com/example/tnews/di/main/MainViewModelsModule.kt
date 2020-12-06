package com.example.tnews.di.main

import androidx.lifecycle.ViewModel
import com.example.tnews.di.viewmodelfactory.ViewModelKey
import com.example.tnews.ui.news.NewsViewModel
import com.example.tnews.ui.sources.SourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SourcesViewModel::class)
    abstract fun bindsNewsViewModel(sourcesViewModel: SourcesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindsSourcesViewModel(newsViewModel: NewsViewModel): ViewModel

}