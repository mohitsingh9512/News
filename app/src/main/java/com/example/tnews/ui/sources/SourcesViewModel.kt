package com.example.tnews.ui.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tnews.network.response.NewsSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SourcesViewModel @Inject constructor(
        private val sourcesRepository : SourcesRepositoryImpl) : ViewModel()  {

    private var _newsSourcesLiveData = MutableLiveData<List<NewsSource>>()

    val newsSourcesLiveData : LiveData<List<NewsSource>>
        get() = _newsSourcesLiveData

    private var _isErrorLiveData = MutableLiveData<Boolean>()

    val isErrorLiveData : LiveData<Boolean>
     get() = _isErrorLiveData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _ , throwable ->
        if(newsSourcesLiveData.value?.isEmpty() == true){
            _isErrorLiveData.value = true
        }
    }

    fun getSources() : LiveData<List<NewsSource>>{
        viewModelScope.launch(Dispatchers.Default + coroutineExceptionHandler) {
            sourcesRepository.getSources(_newsSourcesLiveData)
        }
        return newsSourcesLiveData
    }
}