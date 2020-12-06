package com.example.tnews.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tnews.network.response.NewsSource
import javax.inject.Singleton

@Singleton
@Dao
interface SourcesDao {

    @Query("Select * from sources")
    fun getSources() : List<NewsSource>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertSources(newsSources : List<NewsSource>)
}