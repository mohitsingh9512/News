package com.example.tnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tnews.network.response.Article
import kotlinx.coroutines.*
import javax.inject.Inject

class NewsViewModel @Inject constructor(
        private val newsRepositoryImpl: NewsRepositoryImpl) : ViewModel()  {

    private var _newsLiveData = MutableLiveData<List<Article>>()

    val newsLiveData : LiveData<List<Article>>
        get() = _newsLiveData

    val searchField = MutableLiveData<String>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _ , throwable ->

    }

    fun getNews(sourceId : String) : LiveData<List<Article>> {
        viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
             newsRepositoryImpl.getNews(sourceId,_newsLiveData)
        }
        return newsLiveData
    }

    fun searchLocal(searchText : String, sourceId: String) : LiveData<List<Article>>{
        viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
            newsRepositoryImpl.searchLocal(searchText, sourceId, _newsLiveData)
        }
        return newsLiveData
    }

}