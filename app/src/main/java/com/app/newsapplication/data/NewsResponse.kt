package com.app.newsapplication.data

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)