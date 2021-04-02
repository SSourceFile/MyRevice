package com.fire.myreivces.ui.designMod

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import androidx.databinding.ViewDataBinding
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.databinding.DesignModeActivityBinding
import com.fire.myreivces.ui.designMod.builder.MyBuilder
import com.fire.myreivces.utils.BitmapUtils

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
    var d: MyBuilder = MyBuilder.Builder().setMyName("陶瓷杯").build()
    d.show()

//    var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.splash_bg)
    var bitmap1 = BitmapUtils().disposeBitmap(720, 1080, resources, R.mipmap.splash_bg, false, null);

    ui.showImg.setImageBitmap(bitmap1);
//    logBitmapDetail(bitmap)
    logBitmapDetail(bitmap1)
    handlers.postDelayed(object: Runnable{
      override fun run() {

      }

    }, 1000*30)
//    finish()
  }


  private fun logBitmapDetail(bitmap: Bitmap) {
    Log.e("++++++", "\n 宽度："+bitmap.width+"\n 高度："+bitmap.height+"\n 内存大小："+bitmap.byteCount)
  }

  val handlers = object: Handler(Looper.getMainLooper()){
    override fun handleMessage(msg: Message) {
      super.handleMessage(msg)

    }
  }

}