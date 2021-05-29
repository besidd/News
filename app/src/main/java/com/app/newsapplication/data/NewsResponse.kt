package com.app.newsapplication.data

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)