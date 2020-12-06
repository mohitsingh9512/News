package com.example.tnews.network.response

import androidx.room.*

data class NewsResponse(
    val status : String,
    val totalResults :  String,
    val articles : List<Article>?
)

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @Embedded(prefix = "source_")
    val source : ArticleSource? = null,
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
    val publishedAt : String?,
    @ColumnInfo(name = "content")
    val content : String?
)

data class ArticleSource (
    val id : String?,
    val name : String?
)
