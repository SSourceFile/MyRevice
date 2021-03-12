package com.fire.myreivces.ui.messenger

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

//服务端
class MyMessengerService : Service() {

  private lateinit var messenger: Messenger
  override fun onBind(intent: Intent?): IBinder? {
    messenger = Messenger(MyHandler())
    //服务端返回binder
    return messenger.binder;
  }

  override fun onCreate() {
    super.onCreate()
    Log.e("+++++", "开启服务");
  }


  class MyHandler: Handler(Looper.getMainLooper()){
    override fun handleMessage(msg: Message) {


      when(msg.what){
        1000 ->{
          Log.e("++++", "娃哈哈 "+msg.arg1)
        }
        else-> super.handleMessage(msg)
      }
    }
  }

}