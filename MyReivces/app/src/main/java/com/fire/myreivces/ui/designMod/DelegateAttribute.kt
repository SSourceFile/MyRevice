package com.fire.myreivces.ui.designMod

import android.util.Log
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

class DelegateAttribute {
  //代理属性
  var str: String by Delegate()

//  var da: String by Delegates.notNull<String>().setValue()
}

class Delegate {
  operator fun getValue(delegateAttribute: Any?, property: KProperty<*>): String {
    return "这里委托了属性${property.name}"
  }

  operator fun setValue(delegateAttribute: Any?, property: KProperty<*>, s: String) {
    Log.e("++++","${property.name}属性值 == ${s}")
  }

}






