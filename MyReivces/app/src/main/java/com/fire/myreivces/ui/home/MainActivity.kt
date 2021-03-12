package com.fire.myreivces.ui.home

import android.app.Dialog
import android.content.Intent
import android.view.View
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.ui.coroutines.CoroutinesActivity
import com.fire.myreivces.databinding.ActivityMainBinding
import com.fire.myreivces.ui.datastore.DataStoreActvity
import com.fire.myreivces.http.User
import com.fire.myreivces.ui.compose.ComposeUIActivity
import com.fire.myreivces.ui.designMod.DesignModeActivity
import com.fire.myreivces.ui.dialog.DialogDSL
import com.fire.myreivces.ui.messenger.MessengerActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : BaseVMActivity<MainVM, ActivityMainBinding>(), Clicker {

  override fun vmclazz(): KClass<MainVM> = MainVM::class
  override fun setContentViews(): Int = R.layout.activity_main

  override fun initView() {
    setTheme(R.style.AppTheme)
    super.initView()
    ui.clicker = this
  }

  override fun onClick(v: View?) {
    when (v) {
      save_tst -> {
        var intent = Intent(this, CoroutinesActivity::class.java)
        startActivity(intent)
      }

      get_tst -> {
        var intent = Intent(this, DataStoreActvity::class.java)
        startActivity(intent)
      }
     save ->{
       DialogDSL.showCustomerDialog<User>(this@MainActivity) {
          setDialogSuccess{
            //成功
            var intent = Intent(this@MainActivity, DesignModeActivity::class.java)
            startActivity(intent)
          }
          setDialogErr {
            //失败
          }
        }
      }
      ui.view->{

        var intent = Intent(this, DesignModeActivity::class.java)
        startActivity(intent)
      }
      ui.showCompose->{
        var intent = Intent(this, MessengerActivity::class.java)
        startActivity(intent)
      }
    }
  }

}