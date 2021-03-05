package com.fire.myreivces.ui.compose

import androidx.lifecycle.MutableLiveData
import com.fire.myreivces.base.BaseVM

class ComposeUIVM : BaseVM() {


  val showMe = MutableLiveData<String>().apply { value = "我草无情" }


}