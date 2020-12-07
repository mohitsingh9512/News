package com.example.tnews.ui.sources

import androidx.lifecycle.MutableLiveData
import com.example.tnews.network.response.NewsSource

interface SourcesRepository{
    suspend fun getSources(newsSourceLiveData: MutableLiveData<List<NewsSource>>)
}