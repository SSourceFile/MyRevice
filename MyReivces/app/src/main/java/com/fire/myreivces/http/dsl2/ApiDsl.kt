package com.fire.myreivces.http.dsl2

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelDsl<Response> {

  //发起请求
  internal lateinit var request: suspend () -> Response
  //请求开始
  internal var onStart: (()->Boolean?)? = null
  //请求完成
  internal var onResponse:((Response)->Unit)? = null
  //请求错误
  internal var onError: ((Exception)->Boolean)? = null

  //请求彻底完成
  internal var onFinally:(() ->Boolean)? = null

  infix fun onStart(block: () -> Boolean?){
    this.onStart = block
  }

  infix fun request(request: suspend() ->Response){
    this.request = request
  }

  infix fun onResponse(block: (Response)->Unit){
    this.onResponse = block
  }
  infix fun onError(black: ((Exception) -> Boolean)?){
    this.onError = black
  }
  infix fun onFinally(block: () -> Boolean){
    this.onFinally = block
  }

  internal fun launch(viewModelScope: CoroutineScope){
    viewModelScope.launch(context = Dispatchers.Main) {
      onStart?.invoke()
      try{
        val response = withContext(Dispatchers.IO){
          request()
        }
        onResponse?.invoke(response)
      }catch (e: Exception){
        e.printStackTrace()
        onError?.invoke(e)
      }
    }
  }
}