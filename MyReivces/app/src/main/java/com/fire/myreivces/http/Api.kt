package com.fire.myreivces.http


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

  @GET("/banner/json")
   fun getData(): Call<User>
}