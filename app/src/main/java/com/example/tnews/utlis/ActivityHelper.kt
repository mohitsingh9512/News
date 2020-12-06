package com.example.tnews.utlis

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

class ActivityHelper constructor() {

    fun addFragment(
            activity : FragmentActivity,
            fragment : Fragment,
            containerId : Int,
            addToBackStack : Boolean = false,
            tag : String
    ){
        val manager = activity.supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.apply {
            add(containerId,fragment,tag)
            if(addToBackStack)
                addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }

    fun replaceFragment(
            activity : FragmentActivity,
            fragment : Fragment,
            containerId : Int,
            addToBackStack : Boolean = false
    ){
        val manager = activity.supportFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.apply {
            replace(containerId,fragment)
            if(addToBackStack)
                addToBackStack(fragment.javaClass.simpleName)
            commit()
        }
    }
}