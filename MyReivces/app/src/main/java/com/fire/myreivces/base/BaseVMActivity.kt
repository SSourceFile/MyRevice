package com.fire.myreivces.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.fire.myreivces.BR
import kotlin.reflect.KClass

abstract class BaseVMActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity(){

  val model by lazy { ViewModelProvider(this).get(vmclazz().java) }
  val ui: DB by lazy { DataBindingUtil.setContentView<DB>(this, setContentViews()) }

  override fun onInit() {
    //绑定数据
    ui.setVariable(BR.vm, model)
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