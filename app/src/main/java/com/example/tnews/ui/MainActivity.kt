package com.example.tnews.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.tnews.R
import com.example.tnews.ui.sources.SourcesFragment
import com.example.tnews.utlis.ActivityHelper
import com.example.tnews.utlis.DELAY_IN_BACK_PRESSED
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var mActivityHelper : ActivityHelper

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            initNewsFragment()
        }
    }

    private fun initNewsFragment() {
        val fragment = SourcesFragment.newInstance()
        mActivityHelper.replaceFragment(this,fragment,R.id.container,false)
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if(currentFragment is SourcesFragment){
            if(doubleBackToExitPressedOnce){
                this.finish()
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(this,getString(R.string.back_press_alert),Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                doubleBackToExitPressedOnce = false
            }, DELAY_IN_BACK_PRESSED)
        }else {
            supportFragmentManager.popBackStack()
        }

    }
}