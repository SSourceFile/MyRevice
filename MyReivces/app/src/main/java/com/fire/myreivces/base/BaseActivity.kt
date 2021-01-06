package com.fire.myreivces.base

import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

abstract class BaseActivity : AppCompatActivity(),IActivity, LifecycleOwner, View.OnClickListener{

  //防止handler内存泄露
  val handler by lazy{KHandler(this, Looper.getMainLooper())}
  val activity by lazy { this }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onInit()
  }

  override fun onInit() {
    initView()
    initData()
  }

  override fun initView() {

  }

  override fun initData() {

  }

  override fun onClick(v: View?) {
  }

  override fun onDestroy() {
    super.onDestroy()
  }


}