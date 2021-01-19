package com.fire.myreivces.ui.designMod

import android.util.Log
//委托

//定义接口
interface IBase{
  fun test()
}

//实现接口
class BaseImpl : IBase{
  override fun test() {
    Log.d("++++","数据代理成功")
  }
}

//by代理类
class Computer(test: IBase): IBase by test
