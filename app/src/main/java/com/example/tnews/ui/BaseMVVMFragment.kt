package com.example.tnews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tnews.di.viewmodelfactory.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseMVVMFragment<B : ViewDataBinding> : DaggerFragment() {

    @Inject
    lateinit var viewModelProviderFactory : ViewModelProviderFactory

    @LayoutRes
    protected abstract fun getLayoutResId() : Int

    protected abstract fun onCreateView(instance : Bundle? , binding : B)

    protected fun <V: ViewModel> getViewModel(vClass : Class<V>) : V?{
        if(activity == null)
            return null
        return ViewModelProvider(this, viewModelProviderFactory).get(vClass)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding : B = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        onCreateView(savedInstanceState,binding)
        return binding.root
    }
}