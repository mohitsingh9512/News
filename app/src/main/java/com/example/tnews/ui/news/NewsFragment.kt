package com.example.tnews.ui.news

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tnews.R
import com.example.tnews.databinding.NewsFragmentBinding
import com.example.tnews.network.response.Article
import com.example.tnews.ui.BaseMVVMFragment
import com.example.tnews.ui.webview.WebViewFragment
import com.example.tnews.utlis.ActivityHelper
import com.example.tnews.utlis.NEWS_SOURCE_ID
import com.example.tnews.utlis.NEWS_SOURCE_NAME
import com.example.tnews.utlis.hideKeyboard
import javax.inject.Inject

class NewsFragment : BaseMVVMFragment<NewsFragmentBinding>() , NewsListener{

    private lateinit var newsFragmentBinding: NewsFragmentBinding
    private var newsViewModel : NewsViewModel? = null
    private var sourceId: String? = null
    private var sourceName: String? = null

    @Inject
    lateinit var mActivityHelper: ActivityHelper

    override fun getLayoutResId(): Int {
        return R.layout.news_fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sourceId = it.getString(NEWS_SOURCE_ID)
            sourceName = it.getString(NEWS_SOURCE_NAME)
        }
    }

    override fun onCreateView(instance: Bundle?, binding: NewsFragmentBinding) {
        newsViewModel = getViewModel(NewsViewModel::class.java)
        newsFragmentBinding = binding
        newsFragmentBinding.viewModel = newsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initData()
        setListeners()
        getNews()
        newsViewModel?.searchField?.observe(viewLifecycleOwner,{

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initData(){
        if(!sourceName.isNullOrBlank()){
            newsFragmentBinding.toolbarText.text = sourceName
        }else {
            newsFragmentBinding.toolbarText.text = sourceId
        }
    }
    private fun setListeners(){
        newsFragmentBinding.searchIcon.setOnClickListener {
            newsFragmentBinding.apply {
                etSearch.visibility = View.VISIBLE
                closeIcon.visibility = View.VISIBLE
                searchIcon.visibility = View.GONE
                toolbarText.visibility = View.GONE
            }
        }
        newsFragmentBinding.closeIcon.setOnClickListener {
            newsFragmentBinding.apply {
                context?.hideKeyboard(etSearch)
                etSearch.visibility = View.GONE
                closeIcon.visibility = View.GONE
                searchIcon.visibility = View.VISIBLE
                toolbarText.visibility = View.VISIBLE
            }
        }
        newsFragmentBinding.backIcon.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun getNews() {
        newsViewModel?.getNews(sourceId!!)
        newsFragmentBinding.rvNews.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        newsFragmentBinding.rvNews.adapter = NewsAdapter(this)
    }

    companion object {
        fun newInstance(sourceId : String,sourceName : String?) =
                NewsFragment()
                        .apply {
                            arguments = Bundle().apply {
                                putString(NEWS_SOURCE_ID,sourceId)
                                putString(NEWS_SOURCE_NAME,sourceName)
                            }
                        }
    }

    override fun newsClicked(article : Article) {
        if(!article.url.isNullOrBlank()){
            val fragment = WebViewFragment.newInstance(article.url)
            mActivityHelper.replaceFragment(requireActivity(),fragment,R.id.container,true)
        }
    }

}