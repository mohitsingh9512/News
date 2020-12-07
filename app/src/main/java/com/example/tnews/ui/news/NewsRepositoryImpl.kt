package com.example.tnews.ui.news

import androidx.lifecycle.MutableLiveData
import com.example.tnews.network.MainApiInterface
import com.example.tnews.network.response.Article
import com.example.tnews.persistance.AppDatabase
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor() : NewsRepository{

    @Inject
    lateinit var mainApiInterface: MainApiInterface

    @Inject
    lateinit var appDataSource : AppDatabase

    override suspend fun getNews(sourceId: String, articlesLiveData: MutableLiveData<List<Article>>) {
        val sources = appDataSource.getNewsDao().getArticles(sourceId)
        articlesLiveData.postValue(sources)

        val it = mainApiInterface.getNews(sourceId).execute()
        it.body()?.let {result ->
            if(result.articles != null && result.articles.isNotEmpty()){
                articlesLiveData.postValue(result.articles)
                insertArticlesInDB(result.articles, sourceId)
            }
        }
    }

    override suspend fun searchLocal(searchText: String , sourceId: String, articlesLiveData: MutableLiveData<List<Article>>) {
        val sources = appDataSource.getNewsDao().searchArticles(searchText,sourceId)
        articlesLiveData.postValue(sources)
    }


    private fun insertArticlesInDB(articles : List<Article> , sourceId : String){
        for (article in articles){
            article.sourceId = sourceId
        }
        appDataSource.getNewsDao().insertArticles(articles)
    }
}