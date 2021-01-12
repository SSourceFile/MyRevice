package com.fire.myreivces

import android.app.Application
import com.fire.myreivces.utils.KLog

class BaseApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    KLog.loggable(true).logTag("++++").buildLog()
  }


}