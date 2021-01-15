package com.fire.myreivces.http.dsl

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RequestDsl {

  internal var buildOKHttp: ((OkHttpClient.Builder) -> OkHttpClient.Builder)? = null
  internal var buildRetrofit: ((Retrofit.Builder) -> Retrofit.Builder)? = null
  fun okHttp(builder:((OkHttpClient.Builder) ->OkHttpClient.Builder)) {
    buildOKHttp = builder
  }

  fun retrofit(builder: ((Retrofit.Builder) ->Retrofit.Builder)){
    buildRetrofit = builder
  }
}