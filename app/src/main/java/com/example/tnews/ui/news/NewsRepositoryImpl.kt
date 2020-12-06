package com.example.tnews.ui.news

import androidx.lifecycle.MutableLiveData
import com.example.tnews.network.MainApiInterface
import com.example.tnews.network.response.Article
import com.example.tnews.persistance.AppDatabase
import com.example.tnews.utlis.enqueue
import kotlinx.coroutines.*
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository{

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    private val parentSuperVisor = SupervisorJob()

    private val newsLiveData : MutableLiveData<List<Article>> by lazy {
        MutableLiveData<List<Article>>()
    }

    @Inject
    lateinit var mainApiInterface: MainApiInterface

    @Inject
    lateinit var appDataSource : AppDatabase

    override fun getNews(sourceId : String) : MutableLiveData<List<Article>>{
        scope.launch(parentSuperVisor) {
            val sources = appDataSource.getNewsDao().getArticles()
            newsLiveData.postValue(sources)

            withContext(Dispatchers.Main){
                mainApiInterface.getNews(sourceId).enqueue {
                    onResponse = {
                        it.body()?.let {result ->
                            if(result.articles != null && result.articles.isNotEmpty()){
                                newsLiveData.value = result.articles
                            }
                        }
                    }

                    onFailure = {

                    }
                }
            }
        }

        return newsLiveData
    }

    fun stopProcesses(){
        parentSuperVisor.cancelChildren()
    }

}