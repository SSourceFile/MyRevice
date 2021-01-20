package com.fire.myhttp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

open class RequestViewModel : ViewModel() {


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
    var myDsl = ViewModelDsl<Response>().apply(dsl)
    api<Response> {
      request {
        myDsl.request.invoke()
      }
      onResponse {
        myDsl.onResponse?.invoke(it)
      }

      onError {
        myDsl.onError?.invoke(it)
      }
      onStart {
        myDsl.onStart?.invoke()
      }
    }
  }


}