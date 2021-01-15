package com.fire.myreivces.utils.dispath

import java.util.concurrent.Executor

class Task : ITask {
  override fun priority(): Int {
    return 0
  }

  override fun run() {
    TODO("Not yet implemented")
  }

  override fun runOn(): Executor {
    TODO("Not yet implemented")
  }

  override fun dependsOn(): List<Class<out Task>> {
    TODO("Not yet implemented")
  }

  override fun needWait(): Boolean {
    TODO("Not yet implemented")
  }

  override fun runOnMainThread(): Boolean {
    TODO("Not yet implemented")
  }

  override fun onlyInMainProcess(): Boolean {
    TODO("Not yet implemented")
  }

  override fun getTailRunnable(): Runnable {
    TODO("Not yet implemented")
  }

  override fun setTaskCallBack(taskBack: TaskCallBack) {
    TODO("Not yet implemented")
  }

  override fun needCall(): Boolean {
    TODO("Not yet implemented")
  }

}