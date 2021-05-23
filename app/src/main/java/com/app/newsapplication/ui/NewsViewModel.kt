package com.app.newsapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private var pageNumber = 1
    val breakingNews: LiveData<Resource<NewsResponse>>
        get() = _breakingNews

    init {
        getBreakingNews("in")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        _breakingNews.value = Resource.Loading()

        val response = repository.getBreakingNews(countryCode, pageNumber)
        _breakingNews.value = handleBreakingNews(response)


    }

    private fun handleBreakingNews(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

}