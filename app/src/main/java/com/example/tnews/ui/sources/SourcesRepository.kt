package com.example.tnews.ui.sources

import androidx.lifecycle.LiveData
import com.example.tnews.network.response.NewsSource

interface SourcesRepository{
    fun getSources() : LiveData<List<NewsSource>>
}