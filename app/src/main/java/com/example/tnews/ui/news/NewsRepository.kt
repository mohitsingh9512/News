package com.example.tnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tnews.network.MainApiInterface
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import com.example.tnews.persistance.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

interface NewsRepository {
    fun getNews(sourceId : String) : LiveData<List<Article>>
    fun searchLocal(searchText : String,sourceId : String) : MutableLiveData<List<Article>>
}