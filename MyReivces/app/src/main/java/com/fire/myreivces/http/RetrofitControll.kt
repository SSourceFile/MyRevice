package com.fire.myreivces.http

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

fun <T> CoroutineScope.retrofit(dsl: RetrofitCoroutineDSL<T>.() -> Unit) {
  this.launch(Dispatchers.Main) {
    val coroutineDSL = RetrofitCoroutineDSL<T>().apply(dsl)
    coroutineDSL.api.let { call ->
      val deferred = async(Dispatchers.IO) {
        try {
          call?.execute()
        }
        catch (e: Exception) {
          coroutineDSL.onFailer?.invoke("网络连接出错", -1)
        }
      }
      deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
          //取消网络请求
          call?.cancel()
          coroutineDSL.clean()
        }
      }

      //await 等待异步执行的结果
      val response = deferred.await()
      if (response == null) {
        coroutineDSL.onFailer?.invoke("返回为空", -1)
      } else {
        response.let {
          Log.e("++++", "正常点"+response.toString())
          if (response.isSuccess) {
            //访问接口成功
            if (response.body()?.status == 1) {
              //判断status 为1 表示获取数据成功
              coroutineDSL.onSuccess?.invoke(response.body()!!.data)
            } else {
              coroutineDSL.onFailer?.invoke(response.body()?.msg ?: "返回数据为空", response.code())
            }
          } else {
            coroutineDSL.onFailer?.invoke(response.errorBody().toString(), response.code())
          }
        }
      }
      coroutineDSL.onComplete?.invoke()

    }

  }
}