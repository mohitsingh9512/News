package com.example.tnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tnews.network.response.Article
import javax.inject.Inject

class NewsViewModel @Inject constructor(
        private val newsRepositoryImpl: NewsRepositoryImpl) : ViewModel()  {

    private var _newsLiveData = MutableLiveData<List<Article>>()

    val newsLiveData : LiveData<List<Article>>
        get() = _newsLiveData

    val searchField = MutableLiveData<String>()

    fun getNews(sourceId : String) : LiveData<List<Article>> {
        _newsLiveData = newsRepositoryImpl.getNews(sourceId)
        return newsLiveData
    }

    fun searchLocal(searchText : String, sourceId: String) : LiveData<List<Article>>{
        _newsLiveData = newsRepositoryImpl.searchLocal(searchText,sourceId)
        return newsLiveData
    }

    override fun onCleared() {
        super.onCleared()
        newsRepositoryImpl.stopProcesses()
    }

}