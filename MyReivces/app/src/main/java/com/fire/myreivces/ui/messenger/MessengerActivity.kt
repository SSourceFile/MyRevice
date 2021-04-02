package com.fire.myreivces.ui.messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import androidx.databinding.ViewDataBinding
import com.fire.myreivces.BuildConfig
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.databinding.MessengerActivityBinding
import kotlin.reflect.KClass

//messenger通信
class MessengerActivity : BaseVMActivity<MessengerVM, MessengerActivityBinding>(),Clicker {

  override fun vmclazz(): KClass<MessengerVM> = MessengerVM::class
  override fun setContentViews(): Int = R.layout.messenger_activity

  private var mService: Messenger? = null
  private var bound: Boolean = false

  override fun initView() {
    super.initView()

    ui.clicker = this
    ui.bindTwo.post(object: Runnable{
      override fun run() {

      }
    })
  }

  private fun connectionService() {
    var inte = Intent().apply {
      action = "com.fire.myreivices.Server.Action"
      setPackage(BuildConfig.APPLICATION_ID)
    }.also {
      intent->
    }
    bindService(inte, mServiceConn, Context.BIND_AUTO_CREATE)
  }

  override fun onStop() {
    super.onStop()
    unbindService(mServiceConn)
  }
  private fun stop(){

  }

  private val mServiceConn = object: ServiceConnection{
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      mService = Messenger(service);
      bound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      bound = false
      mService = null
    }
  }

  override fun onClick(v: View?) {
    super.onClick(v)
    when(v){
      ui.bindOne -> {
        connectionService()
      }
      ui.bindTwo -> {
        sayHello()
      }
    }
  }

  private fun sayHello(){
    val message = Message.obtain(null, 1000, 0, 0)
    try {
      mService?.send(message)
    }catch (e: Exception){
      e.printStackTrace()
    }
  }


}