package com.example.tnews.network.response

import androidx.room.*
import com.google.gson.annotations.Expose
import java.sql.Timestamp

data class NewsResponse(
    val status : String,
    val totalResults :  String,
    val articles : List<Article>?
)

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(name = "id")
    val id : Int,
    @Transient
    @ColumnInfo(name = "source")
    var sourceId : String,
    @ColumnInfo(name = "author")
    val author : String?,
    @ColumnInfo(name = "title")
    val title : String?,
    @ColumnInfo(name = "description")
    val description : String?,
    @ColumnInfo(name = "url")
    val url : String?,
    @ColumnInfo(name = "url_to_image")
    val urlToImage : String?,
    @ColumnInfo(name = "published_at")
    val publishedAt : Timestamp?,
    @ColumnInfo(name = "content")
    val content : String?
)