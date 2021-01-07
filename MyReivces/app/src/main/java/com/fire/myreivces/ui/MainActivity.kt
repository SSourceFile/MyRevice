package com.fire.myreivces.ui

import android.content.Intent
import android.view.View
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.databinding.ActivityMainBinding
import com.fire.myreivces.datastore.DataStoreActvity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : BaseVMActivity<MainVM, ActivityMainBinding>(), Clicker {

  override fun vmclazz(): KClass<MainVM> = MainVM::class
  override fun setContentViews(): Int = R.layout.activity_main

  override fun initView() {
    super.initView()
    ui.clicker = this
  }

  override fun onClick(v: View?) {
    when (v) {
      save_tst -> {
      }

      get_tst -> {
        var intent = Intent(this, DataStoreActvity::class.java)
        startActivity(intent)
      }
    }
  }

}