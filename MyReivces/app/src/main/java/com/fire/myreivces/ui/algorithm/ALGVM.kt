package com.fire.myreivces.ui.algorithm

import androidx.lifecycle.MutableLiveData
import com.fire.myreivces.base.BaseVM

class ALGVM : BaseVM() {

  val da = MutableLiveData<String>().apply { value = "挖红薯" }
}