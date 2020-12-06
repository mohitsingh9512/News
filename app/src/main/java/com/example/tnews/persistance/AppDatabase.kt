package com.example.tnews.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import com.example.tnews.utlis.DatabaseConverters
import javax.inject.Singleton

@Database(entities = [NewsSource::class, Article::class], exportSchema = false, version = 1)
@TypeConverters(DatabaseConverters::class)
abstract class AppDatabase : RoomDatabase() {

    @Singleton
    abstract fun getSourcesDao() : SourcesDao

    @Singleton
    abstract fun getNewsDao() : NewsDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}