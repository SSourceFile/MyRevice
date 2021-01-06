package com.fire.myreivces.base

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.fire.myreivces.BR
import kotlin.reflect.KClass

abstract class BaseVMActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseActivity() {

  val model by lazy { ViewModelProvider(this).get(vmclazz().java) }
  val dataBinding: DB by lazy { DataBindingUtil.setContentView<DB>(this, setContentViews()) }

  override fun onInit() {
    //绑定数据
    dataBinding.setVariable(BR.vm, model)
    dataBinding.lifecycleOwner = this
    super.onInit()
  }

  abstract fun vmclazz(): KClass<VM>
  abstract fun setContentViews(): Int

  override fun onDestroy() {
    super.onDestroy()
    dataBinding.unbind()
  }

}