package com.fire.myreivces.http.dsl2

import okhttp3.OkHttpClient
import retrofit2.Retrofit

class RequestDsl {

  internal var builderRetrofit: ((Retrofit.Builder) -> Retrofit.Builder)? = null
  internal var builderOkHttp: ((OkHttpClient.Builder) ->OkHttpClient.Builder)? = null
  infix fun okHttp(builder: (OkHttpClient.Builder) ->OkHttpClient.Builder){
    this.builderOkHttp = builder
  }

  infix fun retrofit(builder: ((Retrofit.Builder)->Retrofit.Builder)?){
    this.builderRetrofit = builder
  }
}