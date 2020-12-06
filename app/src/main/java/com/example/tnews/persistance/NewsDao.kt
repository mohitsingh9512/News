package com.example.tnews.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tnews.network.response.Article
import javax.inject.Singleton

@Singleton
@Dao
interface NewsDao {

    @Query("Select * from articles")
    fun getArticles() : List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles : List<Article>)
}