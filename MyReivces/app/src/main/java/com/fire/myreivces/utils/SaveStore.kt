package com.fire.myreivces.datastore


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences

import com.fire.myreivces.utils.DataStoreUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

suspend fun DataStoreUtils.save(dataStore: DataStore<Preferences>?, key: Preferences.Key<String>, data: Any?){

    dataStore?.edit { mutablePreferences ->
      val value:Any? = mutablePreferences[key] ?: data
      mutablePreferences[key] = value.toString()
    }
}
suspend inline fun DataStoreUtils.get(dataStore: DataStore<Preferences>?,key: Preferences.Key<String>): Flow<Any>? =
    dataStore?.data!!.catch {
      if (it is IOException) {
        it.printStackTrace()
        emit(emptyPreferences())
      }
      else {
        throw it
      }
    }.map { preferences ->
      preferences[key] ?: "出错了没"
    }



