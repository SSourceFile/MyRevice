package com.fire.myreivces.base


import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fire.myreivces.BR
import com.fire.myreivces.R
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.OnBarListener
import com.gyf.immersionbar.OnKeyboardListener
import kotlin.reflect.KClass

abstract class BaseVMActivity<VM : ViewModel, DB : ViewDataBinding> : BaseActivity(){

  val vm by lazy { ViewModelProvider(this).get(vmclazz().java) }
  val ui: DB by lazy { DataBindingUtil.setContentView<DB>(this, setContentViews()) }

  override fun onInit() {
    //绑定数据
    ui.setVariable(BR.vm, vm)
    ui.lifecycleOwner = this
    setStartBar()
    super.onInit()
  }

  private fun setStartBar() {
    ImmersionBar.with(this)
      .statusBarDarkFont(true, 0.2f)
      .navigationBarColor(R.color.c_3c4f5e)
      .init()


  }

  abstract fun vmclazz(): KClass<VM>
  abstract fun setContentViews(): Int

  override fun onDestroy() {
    super.onDestroy()
    ui.unbind()
  }

}