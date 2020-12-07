package com.example.tnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tnews.network.MainApiInterface
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import com.example.tnews.persistance.AppDatabase
import javax.inject.Inject
import javax.inject.Singleton

interface  NewsRepository {
    suspend fun getNews(sourceId : String, articlesLiveData : MutableLiveData<List<Article>>)
    suspend fun searchLocal(searchText : String,sourceId : String, articlesLiveData: MutableLiveData<List<Article>>)
}