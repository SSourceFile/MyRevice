package com.fire.myreivces.datastore

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.fire.myreivces.R
import com.fire.myreivces.base.BaseVMActivity
import com.fire.myreivces.base.Clicker
import com.fire.myreivces.common.CommonSaveKey
import com.fire.myreivces.databinding.DatastoreActivityBinding
import com.fire.myreivces.utils.DataStoreUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

/**
 * datastore案例
 * */
class DataStoreActvity : BaseVMActivity<DataStoreVM, DatastoreActivityBinding>(), Clicker {
  override fun vmclazz(): KClass<DataStoreVM> = DataStoreVM::class
  override fun setContentViews(): Int = R.layout.datastore_activity

  override fun initView() {
    super.initView()
    ui.clicker = this

  }

  override fun onClick(v: View?) {
    super.onClick(v)
    when (v) {
      ui.saveOne -> {
        //存储数据
        lifecycleScope.launch {
          DataStoreUtils.getInstance(activity).saveData("尼玛死基佬", CommonSaveKey.KEY_CODE)
        }
      }

      ui.getOne -> {
        //获取数据
        lifecycleScope.launch {
          DataStoreUtils.getInstance(activity).getData(CommonSaveKey.KEY_CODE)?.collect {
            vm.getData.value = it.toString()
          }
        }
      }
    }
  }
}