package com.fire.myreivces.http


import com.fire.myreivces.base.WanResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

  @GET("/banner/json")
  fun getData(): Call<User>?

  @GET("/banner/json")
  suspend fun getBanner(): WanResponse<List<UserItem>>
}