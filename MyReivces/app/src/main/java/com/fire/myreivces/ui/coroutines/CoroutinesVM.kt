package com.fire.myreivces.ui.coroutines

import androidx.lifecycle.MutableLiveData
import com.fire.myhttp.Request
import com.fire.myhttp.RequestViewModel
import com.fire.myreivces.base.WanResponse
import com.fire.myreivces.http.Api
import com.fire.myreivces.http.UserItem

import kotlinx.coroutines.channels.Channel
import java.lang.Exception

class CoroutinesVM : RequestViewModel() {

  //kotlin队列
  val channel = Channel<Int>()
  val httpData = MutableLiveData<String>()
  val service by lazy { Request.apiService(Api::class.java) }

  //请求banner
  fun loadBanner(onCallBack: OnCallBack) {
    apiDSL<WanResponse<List<UserItem>>> {
      onResponse {
        onCallBack.Success(it)
      }
      request {
        service.getBanner()
      }
      onError {
        onCallBack.failer(it)
      }
    }
  }

  interface OnCallBack {
    fun Success(success: WanResponse<List<UserItem>>)
    fun failer(fail: Exception)
  }
}