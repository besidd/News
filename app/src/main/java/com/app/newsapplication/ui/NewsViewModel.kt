package com.app.newsapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsapplication.data.Article
import com.app.newsapplication.data.NewsResponse
import com.app.newsapplication.repository.NewsRepository
import com.app.newsapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private var _breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingPageNumber = 1
    private var breakingNewsResponse: NewsResponse ?= null
    val breakingNews: LiveData<Resource<NewsResponse>>
        get() = _breakingNews

    private var _searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchPageNumber = 1
    private var searchNewsResponse: NewsResponse ?= null
    val searchNews: LiveData<Resource<NewsResponse>>
        get() = _searchNews

    init {
        getBreakingNews("in")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        _breakingNews.value = Resource.Loading()

        val response = repository.getBreakingNews(countryCode, breakingPageNumber)
        _breakingNews.value = handleBreakingNews(response)

    }

    fun getSearchNews(searchQuery: String) = viewModelScope.launch {
        _searchNews.value = Resource.Loading()

        val response = repository.searchNews(searchQuery, searchPageNumber)
        _searchNews.value = handleSearchNews(response)

    }

    private fun handleBreakingNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                breakingPageNumber++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = it
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = it.articles

                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                searchPageNumber++
                if (searchNewsResponse == null) {
                    searchNewsResponse = it
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = it.articles

                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: it)
            }
        }
        return Resource.Error(response.message())
    }

    fun getSavedNews() = repository.getSavedNews()

    fun saveArticle(article: Article) = viewModelScope.launch {
        repository.saveArticle(article)
    }

    fun delete (article: Article) = viewModelScope.launch {
        repository.deleteArticle(article)
    }

}