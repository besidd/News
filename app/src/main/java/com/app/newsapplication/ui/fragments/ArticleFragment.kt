package com.app.newsapplication.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.app.newsapplication.R
import com.app.newsapplication.ui.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment: Fragment(R.layout.fragment_article) {

    private val viewModel: NewsViewModel by activityViewModels()
}