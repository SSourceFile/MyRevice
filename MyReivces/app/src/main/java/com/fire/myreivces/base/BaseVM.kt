package com.fire.myreivces.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseVM: ViewModel() {

  var data = MutableLiveData<String>().apply { value = "娃哈哈" }


}