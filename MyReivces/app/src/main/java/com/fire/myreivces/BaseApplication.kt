package com.fire.myreivces

import android.app.Application
import com.fire.myreivces.optimize.DynamicConfigImplDemo
import com.fire.myreivces.optimize.TestPluginListener
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig

class BaseApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    initMask()
  }

  private fun initMask() {
    val builder: Matrix.Builder = Matrix.Builder(this) // build matrix

    builder.patchListener(TestPluginListener(this)) // add general pluginListener

    val dynamicConfig = DynamicConfigImplDemo() // dynamic config

    // init plugin

    // init plugin
    val ioCanaryPlugin = IOCanaryPlugin(IOConfig.Builder()
      .dynamicConfig(dynamicConfig)
      .build())
    //add to matrix
    //add to matrix
    builder.plugin(ioCanaryPlugin)

    //init matrix

    //init matrix
    Matrix.init(builder.build())

    // start plugin

    // start plugin
    ioCanaryPlugin.start()
  }
}