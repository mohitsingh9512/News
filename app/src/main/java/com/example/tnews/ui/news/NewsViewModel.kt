package com.example.tnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import com.example.tnews.ui.sources.SourcesRepositoryImpl
import javax.inject.Inject

class NewsViewModel @Inject constructor(
        private val newsRepositoryImpl: NewsRepositoryImpl) : ViewModel()  {

    private var _newsLiveData = MutableLiveData<List<Article>>()

    val newsLiveData : LiveData<List<Article>>
        get() = _newsLiveData

    fun getNews(sourceId : String) : LiveData<List<Article>> {
        _newsLiveData = newsRepositoryImpl.getNews(sourceId)
        return newsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        newsRepositoryImpl.stopProcesses()
    }

}