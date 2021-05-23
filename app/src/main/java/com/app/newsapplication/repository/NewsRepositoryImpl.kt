package com.app.newsapplication.repository

import com.app.newsapplication.api.NewsAPI
import com.app.newsapplication.db.ArticleDatabase

class NewsRepositoryImpl(db: ArticleDatabase, api: NewsAPI): NewsRepository {
}