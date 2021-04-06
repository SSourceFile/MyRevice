package com.fire.myreivces.base

import android.content.Context
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

class BaseFragment: Fragment(), LifecycleOwner, Clicker {

    val handler by lazy{KHandler(this, Looper.getMainLooper())}
    var activity : Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context;
    }

    override fun onClick(v: View?) {

    }
}