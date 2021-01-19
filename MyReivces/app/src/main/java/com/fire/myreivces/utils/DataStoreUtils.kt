package com.fire.myreivces.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import com.fire.myreivces.ui.datastore.get
import com.fire.myreivces.ui.datastore.save

class DataStoreUtils private constructor(context:Context?) {

  var dataStore: DataStore<Preferences>? = null
  companion object{
    @Volatile
    private var instance: DataStoreUtils? = null

    fun getInstance(context:Context?)= instance ?: synchronized(this){
      instance ?: DataStoreUtils(context).also { instance = it }
    }
  }
  private val PREFERENCE_NAME = "DataStore"
  init {
    dataStore = context?.createDataStore(name = PREFERENCE_NAME)
  }

  suspend fun saveData(data: Any?, KEY_CODE: Preferences.Key<String>){
    save(dataStore, KEY_CODE, data)
  }

  suspend fun getData(KEY_CODE: Preferences.Key<String>) =
    get(dataStore, KEY_CODE)

}