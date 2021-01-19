package com.fire.myreivces.ui.designMod

import android.util.Log
import androidx.databinding.ViewDataBinding
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.databinding.DesignModeActivityBinding
import com.fire.myreivces.ui.designMod.builder.MyBuilder
import kotlin.reflect.KClass

class DesignModeActivity : BaseVMActivity<DesignModeVM, DesignModeActivityBinding>() {
  override fun vmclazz(): KClass<DesignModeVM> = DesignModeVM::class
  override fun setContentViews(): Int = R.layout.design_mode_activity

  override fun initData() {
    super.initData()
    Computer(BaseImpl()).test()

    var s = DelegateAttribute().str
//    DelegateAttribute().da = "sssss"
//    Log.e("++++", "娜扎"+s+ "//// "+DelegateAttribute().da )

    //建造者模式
    MyBuilder.Builder().setMyName("陶瓷杯").build()
  }

}