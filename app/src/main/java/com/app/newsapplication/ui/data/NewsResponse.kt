package com.app.newsapplication.ui.data

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)