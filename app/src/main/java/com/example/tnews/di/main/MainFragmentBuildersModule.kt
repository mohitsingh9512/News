package com.example.tnews.di.main

import com.example.tnews.ui.news.NewsFragment
import com.example.tnews.ui.sources.SourcesFragment
import com.example.tnews.ui.webview.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun  contributesSourcesFragment(): SourcesFragment

    @ContributesAndroidInjector
    abstract fun  contributesNewsFragment(): NewsFragment

    @ContributesAndroidInjector
    abstract fun  contributesWebViewFragment(): WebViewFragment
}