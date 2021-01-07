package com.fire.myreivces.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

open class CustomLifeCyclerObs: LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_START)
  public fun method1(){
    Log.e("++++", "生命周期start")

  }

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  public fun method2(){
    Log.e("++++", "声明周期resume")
  }
}