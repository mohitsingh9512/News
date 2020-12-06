package com.example.tnews.ui.news

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tnews.R
import com.example.tnews.databinding.NewsFragmentBinding
import com.example.tnews.databinding.SourcesFragmentBinding
import com.example.tnews.network.response.Article
import com.example.tnews.network.response.NewsSource
import com.example.tnews.ui.BaseMVVMFragment
import com.example.tnews.ui.sources.SourcesAdapter
import com.example.tnews.utlis.NEWS_SOURCE_ID

class NewsFragment : BaseMVVMFragment<NewsFragmentBinding>() , NewsListener{

    private lateinit var newsFragmentBinding: NewsFragmentBinding
    private var newsViewModel : NewsViewModel? = null
    private var sourceId: String? = null

    override fun getLayoutResId(): Int {
        return R.layout.news_fragment
    }

    override fun onCreateView(instance: Bundle?, binding: NewsFragmentBinding) {
        newsViewModel = getViewModel(NewsViewModel::class.java)
        newsFragmentBinding = binding
        newsFragmentBinding.viewModel = newsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        getNews(binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sourceId = it.getString(NEWS_SOURCE_ID)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getNews(binding: NewsFragmentBinding) {
        newsViewModel?.getNews(sourceId!!)
        binding.rvNews.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvNews.adapter = NewsAdapter(this)
    }

    companion object {
        fun newInstance(sourceId : String) =
                NewsFragment()
                        .apply {
                            arguments = Bundle().apply {
                                putString(NEWS_SOURCE_ID,sourceId)
                            }
                        }
    }

    override fun newsClicked(article : Article) {

    }

}