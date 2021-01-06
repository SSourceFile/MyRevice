package com.fire.myreivces.base

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class KHandler(private var lifecycle: LifecycleOwner, looper: Looper): Handler(looper), LifecycleObserver {

  init {
    lifecycle.lifecycle.addObserver(this)
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
  public fun onStop(){

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  public fun onDestory(){
    removeCallbacksAndMessages(null)
    lifecycle.lifecycle.removeObserver(this)
  }

}