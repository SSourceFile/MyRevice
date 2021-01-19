package com.fire.myreivces.ui.coroutines

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.WanResponse
import com.fire.myreivces.databinding.CoroutinesActivityBinding
import com.fire.myreivces.http.Api
import com.fire.myreivces.http.User
import com.fire.myreivces.http.UserItem
import com.fire.myreivces.http.retrofit
import com.zhpan.bannerview.constants.PageStyle
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

/**
 * 协程测试，以及dsl网络请求
 * */
class CoroutinesActivity : BaseVMActivity<CoroutinesVM, CoroutinesActivityBinding>() {
  override fun vmclazz(): KClass<CoroutinesVM> = CoroutinesVM::class
  override fun setContentViews(): Int = R.layout.coroutines_activity
  override fun initView() {
    super.initView()
//    vm.flowTest()
//    vm.cancleFlow()
//    initAwait()
    ui.bannerView.apply {
      setAdapter(BannerAdapter()).create()
      setPageStyle(PageStyle.MULTI_PAGE_OVERLAP)
    }
    initDsl()

//      vm.showChannel()
      vm.iterator()
    vm.channelPro()
  }

  override fun onPause() {
    super.onPause()
    ui.bannerView.stopLoop()
  }

  override fun onResume() {
    super.onResume()
    ui.bannerView.startLoop()
  }

  private fun initDsl() {
    vm.loadBanner(object : CoroutinesVM.OnCallBack {
      override fun Success(success: WanResponse<List<UserItem>>) {
        ui.bannerView.refreshData(success.data)
      }

      override fun failer(fail: Exception) {

      }
    })
  }

  private fun initAwait() {
    vm.viewModelScope.retrofit<User> {
      api = Retrofit.Builder().baseUrl("https://www.wanandroid.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java).getData()
      onSuccess {
        Log.e("+++++", "成功" + it.data?.get(0)?.desc);
        ui.vm?.httpData?.value = it.data?.get(0)?.desc
      }
      onError { msg, _ ->

      }
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    //关闭页面结束协程
  }
}