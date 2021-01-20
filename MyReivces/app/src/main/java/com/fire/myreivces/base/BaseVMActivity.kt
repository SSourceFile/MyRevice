package com.fire.myreivces.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fire.myreivces.BR
import kotlin.reflect.KClass

abstract class BaseVMActivity<VM : ViewModel, DB : ViewDataBinding> : BaseActivity(){

  val vm by lazy { ViewModelProvider(this).get(vmclazz().java) }
  val ui: DB by lazy { DataBindingUtil.setContentView<DB>(this, setContentViews()) }

  override fun onInit() {
    //绑定数据
    ui.setVariable(BR.vm, vm)
    ui.lifecycleOwner = this
    super.onInit()
  }

  abstract fun vmclazz(): KClass<VM>
  abstract fun setContentViews(): Int

  override fun onDestroy() {
    super.onDestroy()
    ui.unbind()
  }

}