package com.fire.myreivces.ui.datastore

import androidx.lifecycle.MutableLiveData
import com.fire.myreivces.base.BaseVM

class DataStoreVM : BaseVM() {

  val saveData = MutableLiveData<String>().apply { value = "存储数据" }
  val getData = MutableLiveData<String>().apply { value = "获取数据" }

}