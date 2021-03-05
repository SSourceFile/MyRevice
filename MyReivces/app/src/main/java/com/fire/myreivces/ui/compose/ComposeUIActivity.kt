package com.fire.myreivces.ui.compose

import androidx.databinding.ViewDataBinding
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.ui.designMod.java.Animal
import com.fire.myreivces.ui.designMod.java.AnimalProxy
import kotlin.reflect.KClass

class ComposeUIActivity : BaseVMActivity<ComposeUIVM, ViewDataBinding>() {
  override fun vmclazz(): KClass<ComposeUIVM>  = ComposeUIVM::class
  override fun setContentViews(): Int = R.layout.compose_activity

  override fun initView() {
    super.initView()
//    val animal = Animal();
//    val a = AnimalProxy(animal);
//    a.fly()

    showOne();
  }

  private fun showOne() {
    val pro = ProxyUtils();
    pro.showOne()


  }

}