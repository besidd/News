package com.app.newsapplication.api

import com.app.newsapplication.data.NewsResponse
import com.app.newsapplication.utils.Constants.API_KEY
import com.app.newsapplication.utils.Urls.BREAKING_NEWS
import com.app.newsapplication.utils.Urls.SEARCH_NEWS
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET(BREAKING_NEWS)
    suspend fun getAllBreakingNews(
        @Query("country")
        CountryCode: String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET(SEARCH_NEWS)
    suspend fun searchForNews(
        @Query("q")
        SearchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

}