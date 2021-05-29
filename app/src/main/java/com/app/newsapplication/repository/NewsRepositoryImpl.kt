package com.app.newsapplication.repository

import com.app.newsapplication.api.NewsAPI
import com.app.newsapplication.data.Article
import com.app.newsapplication.data.NewsResponse
import com.app.newsapplication.db.ArticleDatabase
import retrofit2.Response

class NewsRepositoryImpl(private val db: ArticleDatabase, private val api: NewsAPI) :
    NewsRepository {
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getAllBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> =
        api.searchForNews(searchQuery, pageNumber)

    override suspend fun saveArticle(article: Article): Long = db.getArticleDao().upsert(article)

    override suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)

    override fun getSavedNews() = db.getArticleDao().getAllArticles()
}