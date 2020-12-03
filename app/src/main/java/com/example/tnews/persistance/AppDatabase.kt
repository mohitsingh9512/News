package com.example.tnews.persistance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [],exportSchema = false,version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}