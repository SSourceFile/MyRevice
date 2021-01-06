package com.fire.myreivces.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.datastore.preferences.*
import com.fire.myreivces.R
import com.fire.myreivces.algorithm.ALGActivity
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.databinding.ActivityMainBinding
import com.fire.myreivces.http.User
import com.fire.myreivces.http.retrofit
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KClass

class MainActivity : BaseVMActivity<MainVM, ActivityMainBinding>(), Clicker, CoroutineScope {

  private val PREFERENCE_NAME = "DataStore"

  private lateinit var job: Job
  //定义key
//  private val KEY_CODE = preferencesKey<String>("CodeOne")
//  var dataStore: DataStore<Preferences>? = null

  override fun initView() {
    super.initView()
    job = Job()
    ui.clicker = this
//    get_tst.setOnClickListener(this)
  }

//    dataStore = createDataStore(name = PREFERENCE_NAME)


//    lifecycle.addObserver(CustomLifeCyclerObs())
//    view.setOnClickListener {
//      val intent = Intent(this, AspectActivity::class.java)
//      intent.putExtra("", Bea())
//      startActivity(intent)
//    }
//    save.setOnClickListener {
//      val it = Intent(this, SaveActivity::class.java)
//      startActivity(it)
//    }
//    save_tst.setOnClickListener(this)

//
//
//
////    Log.e("++++", "嘿嘿三生三世" + mainLooper.thread.name + "  //// " + Thread.currentThread().name)
//    Thread {
////      Log.e("++++", "嘿嘿" + Thread.currentThread())
//    }.start()
//    initHandler()
//
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

//    GlobalScope.launch {
//      val token = getToken()
//      getUserInfo(token)
//    }
//
//    repeat(5){
//      Log.e("++++", "嘿嘿${it}")
//    }

//    GlobalScope.launch {
//      val result1 = GlobalScope.async { getResult1() }
//      val result2 = GlobalScope.async { getResult2() }
//
//      val result = result1.await() + result2.await()
//      Log.e("++++", "result = $result")
//    }
//    initAwait()


  private fun initAwait() {
    retrofit<User> {
//      api = Retrofit.Builder().baseUrl("https://www.wanandroid.com")
//        .addConverterFactory(GsonConverterFactory.create())
////        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//        .build().create(Api::class.java).getData()
      onSuccess {

      }
      onError { msg, _ ->

      }
    }
  }

  private suspend fun getResult2():Int{
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
      while (i < 20){
        channel.send(i++)
        delay(1000)
      }
    }
    val consumer = GlobalScope.launch {
      while (true){
        val element = channel.receive();
        Log.e("+++", "////ss "+element.toString())
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

  override fun onClick(v: View?) {
    when (v) {
      save_tst -> {
        //存储数据
//        CoroutineScope(Dispatchers.Main).launch {
//          saveData(dataStore, KEY_CODE)
//        }

      }

      get_tst -> {
//        var myData = readData(KEY_CODE)
//        GlobalScope.launch(Dispatchers.Main) {
//          myData.collect { show_data.text = it }
//        }

        var intent = Intent(this, ALGActivity::class.java)
        startActivity(intent)
      }

    }
  }

//  private fun readData(key: Preferences.Key<String>): Flow<String> =
//    dataStore?.data!!.catch {
//      if (it is IOException) {
//        it.printStackTrace()
//        emit(emptyPreferences())
//      }
//      else {
//        throw it
//      }
//    }.map { preferences ->
//      preferences[key] ?: "出错了没"
//    }

//  private suspend fun saveData(dataStore: DataStore<Preferences>?, key: Preferences.Key<String>) {
//    dataStore?.edit { mutablePreferences ->
//      val value = mutablePreferences[key] ?: "新数据"
//      mutablePreferences[key] = value
//    }
//  }

  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

  override fun vmclazz(): KClass<MainVM> = MainVM::class

  override fun setContentViews(): Int = R.layout.activity_main
}