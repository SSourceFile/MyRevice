package com.fire.myreivces.ui.home.splash

import android.content.Intent
import androidx.databinding.ViewDataBinding
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.ui.home.MainActivity
import kotlin.reflect.KClass

/**
 * 启动页面
 * */
class SplashActivity : BaseVMActivity<SplashVM, ViewDataBinding>(){
    override fun vmclazz(): KClass<SplashVM> = SplashVM::class
    override fun setContentViews(): Int = R.layout.splash_activity

    override fun initView() {
        super.initView()
        handler.postDelayed(object: Runnable{
            override fun run() {
                var intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 5000)
    }
}