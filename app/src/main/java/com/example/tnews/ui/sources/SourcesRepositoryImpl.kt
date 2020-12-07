package com.example.tnews.ui.sources

import androidx.lifecycle.MutableLiveData
import com.example.tnews.network.MainApiInterface
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import com.example.tnews.persistance.AppDatabase
import com.example.tnews.utlis.enqueue
import kotlinx.coroutines.*
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor() : SourcesRepository{

    @Inject
    lateinit var mainApiInterface: MainApiInterface

    @Inject
    lateinit var appDataSource : AppDatabase

    override suspend fun getSources(newsSourceLiveData: MutableLiveData<List<NewsSource>>)  {
        val sources = appDataSource.getSourcesDao().getSources()
        newsSourceLiveData.postValue(sources)

        val it  = mainApiInterface.getSources().execute()
        it.body()?.let {result ->
            if(result.newsSources != null && result.newsSources.isNotEmpty()){
                newsSourceLiveData.postValue(result.newsSources)
                insertNewsSourcesInDB(result.newsSources)
            }
        }
    }

    private fun insertNewsSourcesInDB(newsSources : List<NewsSource>){
        appDataSource.getSourcesDao().insertSources(newsSources)
    }
}