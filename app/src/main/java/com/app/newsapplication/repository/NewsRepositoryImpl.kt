package com.app.newsapplication.repository

import com.app.newsapplication.api.NewsAPI
import com.app.newsapplication.db.ArticleDatabase

class NewsRepositoryImpl(private val db: ArticleDatabase, private val api: NewsAPI) :
    NewsRepository {
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getAllBreakingNews(countryCode, pageNumber)
}