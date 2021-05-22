package com.app.newsapplication.ui.api

import com.app.newsapplication.ui.data.NewsResponse
import com.app.newsapplication.ui.utils.Constants.API_KEY
import com.app.newsapplication.ui.utils.Urls.BREAKING_NEWS
import com.app.newsapplication.ui.utils.Urls.SEARCH_NEWS
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