package com.fire.myreivces.ui.coroutines

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fire.myreivces.utils.KLog
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

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
  val channel = Channel<Int>()
  val data = viewModelScope.launch {
    channel.send(91)
    channel.send(92)
    channel.send(93)
    channel.send(94)
  }
  //channel无法在一个协程里读写
  val sum = viewModelScope.launch {
    val it = channel.iterator()
    while (it.hasNext()){
      val elemet = it.next()
      Log.e("++++", "成功"+elemet)
      delay(2000)
    }
    //或者
    for(element in channel){
      Log.e("++++", "for循环"+element)
    }
  }
  viewModelScope.launch {
    data.join()
    sum.join()
  }
}

//flow的测试
internal fun CoroutinesVM.flowTest() {
  viewModelScope.launch {
    flow<String> { emit("娃哈哈") }.map { return@map "我擦" + it }.collect { Log.e("++++", "是是是" + it) }
  }
}

//取消flow
internal  fun CoroutinesVM.cancleFlow() {
  viewModelScope.launch {
    withTimeoutOrNull(2500) {
      flow<Int> {
        for (i in 1..3) {
          delay(1000)
          emit(i)
        }
      }.collect { Log.e("++++", "消息" + it) }
    }
  }
}


private suspend fun initChannel() {
  val channel = Channel<Int>()
  val product = GlobalScope.launch {
    var i = 0
    while (i < 20) {
      channel.send(i++)
      delay(1000)
    }
  }
  val consumer = GlobalScope.launch {
    while (true) {
      val element = channel.receive();
      Log.e("+++", "////ss " + element.toString())
    }
  }
  product.join()
  consumer.join()
}

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
internal fun CoroutinesVM.channelPro(){
  var i: Int = 0
  val receive:ReceiveChannel<Int> = viewModelScope.produce<Int> {
    while (i < 10){
      i++
      send(i)
    }
  }
  val mdel = viewModelScope.actor<Int> {
    while (true){
      val element = receive()
      Log.e("+++","读取"+element)
    }
  }


  viewModelScope.launch {
    val it = receive.iterator()
    while (it.hasNext()){
      val element = it.next()
      Log.e("+++++", "数据"+element)
    }
  }

}