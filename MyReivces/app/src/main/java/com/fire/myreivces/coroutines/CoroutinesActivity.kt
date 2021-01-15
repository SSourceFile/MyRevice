package com.fire.myreivces.coroutines

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.databinding.CoroutinesActivityBinding
import com.fire.myreivces.http.Api
import com.fire.myreivces.http.User
import com.fire.myreivces.http.retrofit
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

/**
 * 协程测试
 * */
class CoroutinesActivity : BaseVMActivity<CoroutinesVM, CoroutinesActivityBinding>() {
  override fun vmclazz(): KClass<CoroutinesVM> = CoroutinesVM::class
  override fun setContentViews(): Int = R.layout.coroutines_activity

  private lateinit var job: Job
  override fun initView() {
    super.initView()
    job = Job()
//    GlobalScope.launch {
//      flowTest()
//    }
//    runBlocking {
//      cancleFlow()
//    }
//    runBlocking {
////      initChannel()
//      delay(5000);
//      Log.e("++++", "协程执行了")
//    }
//    lifecycleScope.launch {
////      initChannel()
//      delay(2000)
//      Log.e("++++", "当前线程${Thread.currentThread().id}")
//    }
//
//    GlobalScope.launch {
//      val token = getToken()
//      getUserInfo(token)
//    }
//
//    repeat(5){
//      Log.e("++++", "嘿嘿${it}")
//    }
//
//    GlobalScope.launch {
//      val result1 = GlobalScope.async { getResult1() }
//      val result2 = GlobalScope.async { getResult2() }
//
//      val result = result1.await() + result2.await()
//      Log.e("++++", "result = $result")
//    }
    initAwait()

//      vm.showChannel()
//      vm.iterator()
  }

  private fun initAwait() {
    vm.viewModelScope.retrofit<User> {
      api = Retrofit.Builder().baseUrl("https://www.wanandroid.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java).getData()
      onSuccess {
        Log.e("+++++", "成功" + it.data?.get(0)?.desc);
        ui.vm?.httpData?.value = it.data?.get(0)?.desc
      }
      onError { msg, _ ->

      }
    }
  }

  private suspend fun getResult2(): Int {
    delay(2000)
    return 1
  }

  override fun onDestroy() {
    super.onDestroy()
    //关闭页面结束协程
    job.cancel()
  }

  private suspend fun getResult1(): Int {
    delay(6000)
    return 3
  }

  private suspend fun getUserInfo(token: String): String {
    delay(2000)
    Log.e("+++++", "获取的用户信息=$token")
    return ""
  }

  private suspend fun getToken(): String {
    delay(2000)
    return "token"
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

  private suspend fun cancleFlow() {
    withTimeoutOrNull(2500) {
      flow<Int> {
        for (i in 1..3) {
          delay(1000)
          emit(i)
        }
      }.collect { Log.e("++++", "消息" + it) }
    }
  }

  private suspend fun flowTest() {
    flow<String> { emit("娃哈哈") }.map { return@map "我擦" + it }.collect { Log.e("++++", "是是是" + it) }
  }

  private fun initHandler() {
    var handler = @SuppressLint("HandlerLeak")
    object : Handler(Looper.getMainLooper()) {
      override fun handleMessage(msg: Message) {
        super.handleMessage(msg)

      }
    }

    var msg = Message.obtain()
    msg.what = 1
    handler.sendMessageDelayed(msg, 1000)

  }
}