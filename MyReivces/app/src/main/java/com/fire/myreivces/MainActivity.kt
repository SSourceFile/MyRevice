package com.fire.myreivces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.fire.myreivces.aspect.AspectActivity
import com.fire.myreivces.view.ViewGroupActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    view.setOnClickListener {
      val intent = Intent(this, AspectActivity::class.java)
      intent.putExtra("", Bea())
      startActivity(intent)
    }

    Log.e("++++","嘿嘿三生三世"+mainLooper.thread.name+ "  //// "+Thread.currentThread ().name)
    Thread {
      Log.e("++++","嘿嘿"+Thread.currentThread())
    }.start()
    initHandler()
  }

  private fun initHandler() {
    var handler = object: Handler(){
      override fun handleMessage(msg: Message) {
        super.handleMessage(msg)

      }
    }

    var msg = Message.obtain()
    msg.what = 1
    handler.sendMessageDelayed(msg, 1000)
  }
}