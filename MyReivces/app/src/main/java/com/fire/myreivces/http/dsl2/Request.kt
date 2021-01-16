package com.fire.myreivces.http.dsl2

import android.content.Context
import com.fire.myreivces.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object Request {

  internal lateinit var appContext: Context
  internal lateinit var retrofit: Retrofit
  internal lateinit var baseUrl: String
//  internal lateinit var headers: HeaderInterceptor

  fun <Service> apiService(service: Class<Service>): Service{
    return retrofit.create(service)
  }
  private var requestDsl: (RequestDsl.() ->Unit)? = null
  fun init(context: Context, baseUrl: String, dsl: (RequestDsl.() ->Unit)? = null) {
    this.appContext = context
    this.baseUrl = baseUrl
    this.requestDsl = dsl
    init(dsl)
  }

  private fun init(requestDsl: (RequestDsl.() -> Unit)? = null) {
    initRetrofit(getOkHttp(), requestDsl)
  }

  //初始化retrofit
  private fun initRetrofit(okHttpBuilder: OkHttpClient.Builder, dsl: (RequestDsl.() -> Unit)? = null) {
    val dsl = if (dsl != null) RequestDsl().apply(dsl) else null
    //找寻okhttp请求
    var okHttp = dsl?.builderOkHttp?.invoke(okHttpBuilder) ?: okHttpBuilder
    //找寻retrofit请求
    var retrofit = Retrofit.Builder()
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .client(okHttp.build())
    this.retrofit = retrofit.build()
  }

  //初始化okHttp
  private fun getOkHttp(): OkHttpClient.Builder {
    var builder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
      try {
        val sslContext = SSLContext.getInstance("SSL")
//        sslContext.init(null, arrayOf(X509TrustManager()))
        builder.hostnameVerifier(HostnameVerifier { hostname, session -> true })
//        builder.addNetworkInterceptor(LoggingInterceptor())
      }
      catch (e: Exception) {
        throw RuntimeException(e)
      }
    }
    return builder
      .cache(Cache(appContext.cacheDir, 10 * 1024 * 1024L))  //设置缓存大小和路径
//      .addInterceptor(headers)    //设置请求头
      .connectTimeout(10, TimeUnit.SECONDS)   //设置超时时间
      .readTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)

  }
}