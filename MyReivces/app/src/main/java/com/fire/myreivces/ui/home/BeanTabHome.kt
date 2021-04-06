package com.fire.myreivces.ui.home

import androidx.lifecycle.MutableLiveData
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseFragment

class BeanTabHome(val fragment: BaseFragment,
                  private val ic: Int, private val icOn: Int,
                  private val colorText: Int = R.color.c_9495ab, private val colorTextOn: Int = R.color.btn_on) {
    //=================== ===================
    val text = MutableLiveData<String>()
    val isSelect = MutableLiveData<Boolean>()
    val textColor = MutableLiveData<Int>()
    val iv = MutableLiveData<Int>()

    //=================== ===================
    fun update(isSelect: Boolean) {
        isSelect.let {
            this.isSelect.value = it
            textColor.value = if (it) colorTextOn else colorText
            iv.value = if (it) icOn else ic
        }
    }
}
