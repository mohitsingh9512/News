package com.example.tnews.ui

import android.os.Bundle
import com.example.tnews.R
import com.example.tnews.ui.sources.SourcesFragment
import com.example.tnews.utlis.ActivityHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mActivityHelper : ActivityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNewsFragment()
    }

    private fun initNewsFragment() {
        val fragment = SourcesFragment.newInstance()
        mActivityHelper.replaceFragment(this,fragment,R.id.container,false)
    }
}