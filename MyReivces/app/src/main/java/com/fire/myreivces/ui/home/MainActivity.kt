package com.fire.myreivces.ui.home

import android.util.Log
import android.view.View
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : BaseVMActivity<MainVM, ActivityMainBinding>(), Clicker {

  override fun vmclazz(): KClass<MainVM> = MainVM::class
  override fun setContentViews(): Int = R.layout.activity_main

  override fun initView() {
    setTheme(R.style.AppTheme)
    super.initView()
    ui.clicker = this
    var w = resources.displayMetrics.widthPixels;
    var h = resources.displayMetrics.heightPixels;
    Log.e("++++", "////"+w+" ////  "+h);

  }

  override fun onClick(v: View?) {
    when (v) {
//      save_tst -> {
//        var intent = Intent(this, CoroutinesActivity::class.java)
//        startActivity(intent)
//      }
//
//      get_tst -> {
//        var intent = Intent(this, DataStoreActvity::class.java)
//        startActivity(intent)
//      }
//     save ->{
//       DialogDSL.showCustomerDialog<User>(this@MainActivity) {
//          setDialogSuccess{
//            //成功
//            var intent = Intent(this@MainActivity, DesignModeActivity::class.java)
//            startActivity(intent)
//          }
//          setDialogErr {
//            //失败
//          }
//        }
//      }
//      ui.view->{
//
//        var intent = Intent(this, DesignModeActivity::class.java)
//        startActivity(intent)
//      }
//      ui.showCompose->{
//        var intent = Intent(this, MessengerActivity::class.java)
//        startActivity(intent)
//      }
    }

  }

}