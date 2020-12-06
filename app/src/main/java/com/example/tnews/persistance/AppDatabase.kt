package com.example.tnews.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import javax.inject.Singleton

@Database(entities = [NewsSource::class , Article::class],exportSchema = false,version = 1)
abstract class AppDatabase : RoomDatabase() {

    @Singleton
    abstract fun getSourcesDao() : SourcesDao

    @Singleton
    abstract fun getNewsDao() : NewsDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}