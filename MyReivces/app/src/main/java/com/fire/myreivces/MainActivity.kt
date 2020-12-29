package com.fire.myreivces

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.fire.myreivces.aspect.AspectActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {

  private val PREFERENCE_NAME = "DataStore"

  //定义key
  private val KEY_CODE = preferencesKey<String>("CodeOne")
  var dataStore: DataStore<Preferences>? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dataStore = createDataStore(name = PREFERENCE_NAME)
    setContentView(R.layout.activity_main)
    view.setOnClickListener {
      val intent = Intent(this, AspectActivity::class.java)
      intent.putExtra("", Bea())
      startActivity(intent)
    }
    save.setOnClickListener {
      val it = Intent(this, SaveActivity::class.java)
      startActivity(it)
    }
    save_tst.setOnClickListener(this)
    get_tst.setOnClickListener(this)



    Log.e("++++", "嘿嘿三生三世" + mainLooper.thread.name + "  //// " + Thread.currentThread().name)
    Thread {
      Log.e("++++", "嘿嘿" + Thread.currentThread())
    }.start()
    initHandler()

    GlobalScope.launch {
      flowTest()
    }
    runBlocking {
      cancleFlow()
    }

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
        CoroutineScope(Dispatchers.Main).launch {
          saveData(dataStore, KEY_CODE)
        }

      }

      get_tst -> {
        var myData = readData(KEY_CODE)
        GlobalScope.launch(Dispatchers.Main) {
          myData.collect { show_data.text = it }
        }
      }

    }
  }

  private fun readData(key: Preferences.Key<String>): Flow<String> =
    dataStore?.data!!.catch {
      if (it is IOException) {
        it.printStackTrace()
        emit(emptyPreferences())
      }
      else {
        throw it
      }
    }.map { preferences ->
      preferences[key] ?: "出错了没"
    }

  private suspend fun saveData(dataStore: DataStore<Preferences>?, key: Preferences.Key<String>) {
    dataStore?.edit { mutablePreferences ->
      val value = mutablePreferences[key] ?: "新数据"
      mutablePreferences[key] = value
    }
  }
}