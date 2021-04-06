package com.fire.myreivces.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.box.BoxFragment
import com.example.home.HomeFragment
import com.example.mine.MineFragment
import com.example.receive.ReceiveFragment
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseFragment
import com.fire.myreivces.base.BaseVM

class MainVM : BaseVM() {

    val tab0 = BeanTabHome(HomeFragment(), R.mipmap.ic_home_normal, R.mipmap.ic_home_checked)
    val tab1 = BeanTabHome(ReceiveFragment(), R.mipmap.ic_receive_normal, R.mipmap.ic_receive_checked)
    val tab2 = BeanTabHome(BoxFragment(), R.mipmap.ic_box_normal, R.mipmap.ic_box_checked)
    val tab3 = BeanTabHome(MineFragment(), R.mipmap.ic_mine_normal, R.mipmap.ic_mine_checked)


    fun onUpdate(){
//        tab0.text.value =
    }


}