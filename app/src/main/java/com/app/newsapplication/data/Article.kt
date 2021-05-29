package com.app.newsapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.newsapplication.utils.Constants.ARTICLE_TABLE_NAME
import java.io.Serializable

@Entity(tableName = ARTICLE_TABLE_NAME)
data class Article(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Serializable