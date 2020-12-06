package com.example.tnews.network.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    val status : String,
    @SerializedName("sources")
    @Expose
    val newsSources : List<NewsSource>?
)

@Entity(tableName = "sources")
data class NewsSource(
    @PrimaryKey
    @Expose(serialize = false, deserialize = false)
    @ColumnInfo(name = "id")
    val id : String,
    @ColumnInfo(name = "name")
    val name : String?,
    @ColumnInfo(name = "description")
    val description : String?,
    @ColumnInfo(name = "url")
    val url : String?,
    @ColumnInfo(name = "category")
    val category : String?,
    @ColumnInfo(name = "language")
    val language : String?,
    @ColumnInfo(name = "country")
    val country : String?
){
    fun isSourceUrl() = true
}