package com.app.newsapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.newsapplication.R
import com.app.newsapplication.adapters.NewsAdapter
import com.app.newsapplication.ui.NewsViewModel
import com.app.newsapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_breaking_news.*

private const val TAG = "BreakingNewsFragment"

@AndroidEntryPoint
class BreakingNewsFragment: Fragment(R.layout.fragment_breaking_news) {

    private val viewModel: NewsViewModel by activityViewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter()
        rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Error -> {
                    response.message?.let {
                        Log.d(TAG, "onViewCreated: $it")
                    }
                }
                is Resource.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading")
                }
            }
        })
    }
}