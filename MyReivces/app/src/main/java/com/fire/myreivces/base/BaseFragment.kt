package com.fire.myreivces.base

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM: ViewModel, DB: ViewDataBinding>: Fragment(), LifecycleOwner, Clicker {

    val handler by lazy{KHandler(this, Looper.getMainLooper())}
    var activity : Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context;
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(activity).inflate(setContentViews(), container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }
    abstract fun vmclazz(): KClass<VM>
    abstract fun setContentViews(): Int
    private fun initData() {

    }

    private fun initView() {

    }

    override fun onClick(v: View?) {

    }
}