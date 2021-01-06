package com.fire.myreivces.algorithm

import androidx.lifecycle.MutableLiveData
import com.fire.myreivces.base.BaseViewModel

class ALGVM : BaseViewModel() {

  val da = MutableLiveData<String>().apply { value = "挖红薯" }
}