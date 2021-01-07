package com.fire.myreivces.lifecycle

import androidx.databinding.ViewDataBinding
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.databinding.LiftcycleActivityBinding
import kotlin.reflect.KClass

class LifecycActivity : BaseVMActivity<LifecycVM, LiftcycleActivityBinding>() {
  override fun vmclazz(): KClass<LifecycVM> = LifecycVM::class
  override fun setContentViews(): Int = R.layout.liftcycle_activity

  override fun initView() {
    super.initView()
    lifecycle.addObserver(CustomLifeCyclerObs())
  }
}