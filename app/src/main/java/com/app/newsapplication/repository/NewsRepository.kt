package com.app.newsapplication.repository

import androidx.lifecycle.LiveData
import com.app.newsapplication.data.Article
import com.app.newsapplication.data.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse>

    suspend fun saveArticle(article: Article): Long

    suspend fun deleteArticle(article: Article)

    fun getSavedNews(): LiveData<List<Article>>

}