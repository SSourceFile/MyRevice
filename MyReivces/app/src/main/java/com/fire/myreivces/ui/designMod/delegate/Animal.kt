package com.fire.myreivces.ui.designMod.delegate

import android.util.Log

interface Animal {

  fun back()
}

class Cat : Animal{
  override fun back() {
    Log.e("++++", "嘿嘿")
  }

}

class Zoo(animal: Animal): Animal by animal{

}