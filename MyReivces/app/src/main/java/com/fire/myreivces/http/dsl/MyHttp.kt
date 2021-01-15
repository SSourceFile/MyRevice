package com.fire.myreivces.http.dsl

import okhttp3.OkHttpClient

class MyHttp {

  private fun initRequest(okHttpBuilder: OkHttpClient.Builder, requestDsl: (RequestDsl.() -> Unit)? = null){
//    val dsl = if(requestDsl != null){RequestDsl().apply { requestDsl }}
  }
}