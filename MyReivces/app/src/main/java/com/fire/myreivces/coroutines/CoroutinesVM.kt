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

class CoroutinesVM : RequestViewModel() {

  val channel = Channel<Int>()

  val httpData = MutableLiveData<String>()
  val service by lazy { Request.apiService(Api::class.java)}
  fun loadBanner(){


    apiDSL<WanResponse<List<UserItem>>> {
      onResponse {
        KLog.e("++++"+it.data)
      }

      request {
        service.getData()
      }
    }
  }
}