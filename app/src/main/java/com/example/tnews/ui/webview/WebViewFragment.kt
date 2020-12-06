package com.example.tnews.ui.webview

import android.os.Bundle
import android.view.View
import com.example.tnews.R
import com.example.tnews.databinding.WebViewFragmentBinding
import com.example.tnews.ui.BaseMVVMFragment
import com.example.tnews.ui.news.NewsFragment
import com.example.tnews.utlis.NEWS_WEB_VIEW_URL

class WebViewFragment : BaseMVVMFragment<WebViewFragmentBinding>() {

    private lateinit var webViewFragmentBinding: WebViewFragmentBinding
    private var newsWebViewUrl: String? = null

    override fun getLayoutResId(): Int {
        return R.layout.web_view_fragment
    }

    override fun onCreateView(instance: Bundle?, binding: WebViewFragmentBinding) {
        webViewFragmentBinding = binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsWebViewUrl = it.getString(NEWS_WEB_VIEW_URL)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsWebViewUrl?.let {
            webViewFragmentBinding.webView.loadUrl(it)
        }
    }

    companion object {
        fun newInstance(newWebViewUrl : String) =
            WebViewFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(NEWS_WEB_VIEW_URL,newWebViewUrl)
                    }
                }
    }
}