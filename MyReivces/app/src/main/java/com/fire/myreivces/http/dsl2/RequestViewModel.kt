package com.fire.myreivces.http.dsl2


import androidx.lifecycle.viewModelScope
import com.fire.myreivces.base.BaseVM

open class RequestViewModel : BaseVM() {


  //调用协程进行请求
  private fun <Response> api(dsl: ViewModelDsl<Response>.() -> Unit){
     ViewModelDsl<Response>().apply(dsl).launch(viewModelScope)
  }

  @JvmOverloads
  protected fun <Response> apiCallback(
    request: suspend () -> Response,
    onResponse: ((Response)->Unit),
    onStart: (() -> Boolean)? = null,
    onError: ((Exception) -> Boolean)? = null,
    onFinaly: (() -> Boolean)? = null){
    api<Response> {
      onResponse {
        onResponse.invoke(it)
      }
      request {
        request.invoke()
      }
      onStart {
        onStart?.invoke()
        false
      }
      onError {
        onError?.invoke(it)
        false
      }
    }
  }

  protected fun <Response> apiDSL(dsl: ViewModelDsl<Response>.() -> Unit){
    api<Response> {

    }
  }


}