package com.fire.myreivces

import android.app.Application
import com.fire.myreivces.http.dsl2.Request
import com.fire.myreivces.utils.KLog

class BaseApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    KLog.loggable(true).logTag("++++").buildLog()

    Request.init(this, "https://www.wanandroid.com")
  }


}