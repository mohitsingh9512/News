package com.example.tnews.ui.sources

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tnews.R
import com.example.tnews.databinding.SourcesFragmentBinding
import com.example.tnews.network.response.NewsSource
import com.example.tnews.ui.BaseMVVMFragment
import com.example.tnews.ui.news.NewsFragment
import com.example.tnews.utlis.ActivityHelper
import javax.inject.Inject

class SourcesFragment : BaseMVVMFragment<SourcesFragmentBinding>() , SourcesListener {

    @Inject
    lateinit var mActivityHelper: ActivityHelper

    private lateinit var sourcesFragmentBinding: SourcesFragmentBinding
    private var sourceViewModel : SourcesViewModel? = null

    override fun getLayoutResId(): Int {
        return R.layout.sources_fragment
    }

    override fun onCreateView(instance: Bundle?, binding: SourcesFragmentBinding) {
        sourcesFragmentBinding = binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sourceViewModel = getViewModel(SourcesViewModel::class.java)
        sourcesFragmentBinding.viewModel = sourceViewModel
        sourcesFragmentBinding.lifecycleOwner = viewLifecycleOwner
        initData()
        getSources()
    }

    private fun initData(){
        sourcesFragmentBinding.rvSources.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
        )
        sourcesFragmentBinding.rvSources.adapter = SourcesAdapter(this)
    }

    private fun getSources() {
        sourceViewModel?.getSources()
    }

    companion object {
        fun newInstance() =
            SourcesFragment()
                    .apply {
                        arguments = Bundle().apply {

                        }
                    }
    }

    override fun sourceClicked(newsSource : NewsSource) {
        val fragment = NewsFragment.newInstance(newsSource.id,newsSource.name)
        mActivityHelper.replaceFragment(requireActivity(),fragment,R.id.container,true)
    }

}