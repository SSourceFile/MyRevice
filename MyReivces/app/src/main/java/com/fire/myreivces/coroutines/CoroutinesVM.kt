package com.fire.myreivces.coroutines

import androidx.lifecycle.MutableLiveData
import com.fire.myreivces.base.BaseVM
import com.fire.myreivces.base.WanResponse
import com.fire.myreivces.http.Api
import com.fire.myreivces.http.UserItem
import com.fire.myreivces.http.dsl2.Request
import com.fire.myreivces.http.dsl2.RequestDsl
import com.fire.myreivces.http.dsl2.RequestViewModel
import com.fire.myreivces.utils.KLog
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