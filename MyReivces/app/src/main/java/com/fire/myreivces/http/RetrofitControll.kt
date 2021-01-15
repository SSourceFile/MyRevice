package com.fire.myreivces.http

import com.fire.myreivces.utils.KLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

fun <T> CoroutineScope.retrofit(dsl: RetrofitCoroutineDSL<T>.() -> Unit) {
  this.launch(Dispatchers.Main) {
    val coroutineDSL = RetrofitCoroutineDSL<T>().apply(dsl)
    coroutineDSL.api.let { call ->
      val deferred = async(Dispatchers.IO) {
        try {
          val response = call?.execute()
          response.let {
            if (response?.isSuccessful == true) {
              //访问接口成功
              if (response.code() == 200) {
                //判断status 为1 表示获取数据成功
                response.body()?.let { it1 -> coroutineDSL.onSuccess?.invoke(it1) }
              }
              else {
                coroutineDSL.onFailer?.invoke(response.message() ?: "返回数据为空", response.code())
              }
            }
            else {
              response?.code()?.let { it1 -> coroutineDSL.onFailer?.invoke(response?.errorBody().toString(), it1) }
            }
          }
        }
        catch (e: Exception) {
          coroutineDSL.onFailer?.invoke("网络连接出错", -1)
        }
      }
      deferred.invokeOnCompletion {
        if (deferred.isCancelled) {
          //取消网络请求
          KLog.e("网络请求完毕，关闭")
          call?.cancel()
          coroutineDSL.clean()
        }
      }
      coroutineDSL.onComplete?.invoke()
    }

  }
}