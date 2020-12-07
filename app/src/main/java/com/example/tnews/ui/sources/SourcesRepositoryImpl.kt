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

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val parentSuperVisor = SupervisorJob()

    private val newsSourcesLiveData : MutableLiveData<List<NewsSource>> by lazy {
        MutableLiveData<List<NewsSource>>()
    }

    @Inject
    lateinit var mainApiInterface: MainApiInterface

    @Inject
    lateinit var appDataSource : AppDatabase

    override fun getSources() : MutableLiveData<List<NewsSource>>{
        scope.launch(parentSuperVisor) {
            val sources = appDataSource.getSourcesDao().getSources()
            newsSourcesLiveData.postValue(sources)

            withContext(Dispatchers.Main){
                mainApiInterface.getSources().enqueue {
                    onResponse = {
                        it.body()?.let {result ->
                            if(result.newsSources != null && result.newsSources.isNotEmpty()){
                                newsSourcesLiveData.value = result.newsSources
                                insertNewsSourcesInDB(result.newsSources)
                            }
                        }
                    }

                    onFailure = {

                    }
                }
            }
        }

        return newsSourcesLiveData
    }

    private fun insertNewsSourcesInDB(newsSources : List<NewsSource>){
        scope.launch(parentSuperVisor) {
            appDataSource.getSourcesDao().insertSources(newsSources)
        }
    }

    fun stopProcesses(){
        parentSuperVisor.cancelChildren()
    }

}