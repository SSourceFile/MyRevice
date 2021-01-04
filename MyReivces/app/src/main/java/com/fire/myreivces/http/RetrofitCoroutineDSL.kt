package com.fire.myreivces.http

import retrofit2.Call

class RetrofitCoroutineDSL<T> {

  var api: (Call<Result<T>>)? = null

  internal var onSuccess: ((T) -> Unit)? = null
    private set
  internal var onFailer: ((msg: String, errCode: Int) -> Unit)? = null
    private set
  internal var onComplete: (() -> Unit)? = null
    private set

  //获取数据成功
  fun onSuccess(block: (T) -> Unit) {
    this.onSuccess = block
  }

  //获取数据失败
  fun onError(block: (msg: String, errCode: Int) -> kotlin.Unit) {
    this.onFailer = block
  }

  //请求完成
  fun onComplete(block: () -> Unit) {
    this.onComplete = block
  }

  internal fun clean() {
    onSuccess = null
    onFailer = null
    onComplete = null
  }

}