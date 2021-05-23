package com.app.newsapplication.repository

import com.app.newsapplication.data.NewsResponse
import retrofit2.Response

interface NewsRepository {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

}