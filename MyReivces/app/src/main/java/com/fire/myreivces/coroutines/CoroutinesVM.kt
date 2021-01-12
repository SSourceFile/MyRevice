package com.fire.myreivces.coroutines

import com.fire.myreivces.base.BaseVM
import kotlinx.coroutines.channels.Channel

class CoroutinesVM : BaseVM() {

  val channel = Channel<Int>()
}