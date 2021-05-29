package com.app.newsapplication.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.newsapplication.R
import com.app.newsapplication.adapters.NewsAdapter
import com.app.newsapplication.data.Article
import com.app.newsapplication.ui.NewsViewModel
import com.app.newsapplication.utils.Constants.SEARCH_TIME_DELAY
import com.app.newsapplication.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_news.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "SearchNewsFragment"

@AndroidEntryPoint
class SearchNewsFragment : Fragment(R.layout.fragment_search_news), (Article) -> Unit {

    private val viewModel: NewsViewModel by activityViewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job: Job? = null

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                job?.cancel()
                job = MainScope().launch {
                    delay(SEARCH_TIME_DELAY)
                    char?.let {
                        if (char.toString().isNotEmpty()) {
                            viewModel.getSearchNews(char.toString())
                        }
                    }
                }
            }
        })

        newsAdapter = NewsAdapter(this@SearchNewsFragment)
        rvSearch.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
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

    override fun invoke(article: Article) {
        val action = SearchNewsFragmentDirections.actionSearchNewsFragmentToArticleFragment(article)
        findNavController().navigate(action)
    }
}