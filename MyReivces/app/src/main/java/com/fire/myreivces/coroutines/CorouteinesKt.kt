package com.fire.myreivces.coroutines

import androidx.lifecycle.viewModelScope
import com.fire.myreivces.utils.KLog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun CoroutinesVM.showChannel() {
  //第一个协程
  var pro = viewModelScope.launch {
    var i = 0
    while (true) {
      channel.send(i++)
      delay(1000)
    }
  }
  //第二个协程
  var consumer = viewModelScope.launch {
    while (true) {
      val elem = channel.receive()
      KLog.v("测试论文");
      KLog.e("heihei")
    }
  }

  viewModelScope.launch {
    pro.join()
    consumer.join()
  }
}

fun CoroutinesVM.iterator(){
  val sum = viewModelScope.launch {
    val it = channel.iterator()
    while (it.hasNext()){
      val elemet = it.next()
      KLog.e("迭代器")
      delay(2000)
    }
  }
  viewModelScope.launch {
    sum.join()
  }
}