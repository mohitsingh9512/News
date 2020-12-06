package com.example.tnews.ui.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tnews.network.response.NewsSource
import javax.inject.Inject

class SourcesViewModel @Inject constructor(
        private val sourcesRepository : SourcesRepositoryImpl) : ViewModel()  {

    private var _newsSourcesLiveData = MutableLiveData<List<NewsSource>>()

    val newsSourcesLiveData : LiveData<List<NewsSource>>
        get() = _newsSourcesLiveData


    fun getSources() : LiveData<List<NewsSource>>{
        _newsSourcesLiveData = sourcesRepository.getSources()
        return newsSourcesLiveData
    }

    override fun onCleared() {
        super.onCleared()
        sourcesRepository.stopProcesses()
    }

}