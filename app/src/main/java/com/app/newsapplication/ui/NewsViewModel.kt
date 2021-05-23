package com.app.newsapplication.ui

import androidx.lifecycle.ViewModel
import com.app.newsapplication.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {



}