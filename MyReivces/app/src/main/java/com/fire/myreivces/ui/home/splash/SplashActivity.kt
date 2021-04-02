package com.fire.myreivces.ui.home.splash

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.databinding.SplashActivityBinding
import com.fire.myreivces.ui.home.MainActivity
import com.fire.myreivces.utils.BitmapUtils
import kotlin.reflect.KClass

/**
 * 启动页面
 * */
class SplashActivity : BaseVMActivity<SplashVM, SplashActivityBinding>(), Clicker {
  override fun vmclazz(): KClass<SplashVM> = SplashVM::class
  override fun setContentViews(): Int = R.layout.splash_activity

  override fun initView() {
    super.initView()
    ui.click = this
    handler.postDelayed(object : Runnable {
      override fun run() {
        jumpHome()
      }
    }, 5000)
  }

  private fun jumpHome() {
    var intent = Intent(activity, MainActivity::class.java)
    startActivity(intent)
    finish()
  }

  override fun initData() {
    super.initData()
//    ui.splashImg.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.splash_bg))
    ui.splashImg.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_meizi3))

//    var drawableMZ:BitmapDrawable = ContextCompat.getDrawable(this, R.mipmap.ic_meizi1) as BitmapDrawable;
//    var bit: Bitmap? = compressBmpFromBmp(drawableMZ.bitmap)
//    ui.splashImg.setImageBitmap(bit);
  }

  override fun onClick(v: View?) {
    super.onClick(v)
    when (v) {
      ui.jump -> {
        jumpHome()
      }
    }
  }
}