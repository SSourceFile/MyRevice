package com.fire.myreivces.view

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.fire.myreivces.R

/**
 * 事件分发机制https://www.cnblogs.com/normalandy/p/12409526.html
 * */
class ViewGroupActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.group_activity)
  }

  override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
    Log.e("+++++", "Activity /////  dispathch")
    return super.dispatchTouchEvent(ev)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {
    when(event?.action){
      MotionEvent.ACTION_DOWN->{
        Log.e("+++++", "Activity /////  down   onTouchEvent")
      }
      MotionEvent.ACTION_UP->{
        Log.e("+++++", "Activity ///// up    onTouchEvent")
      }
    }

    return super.onTouchEvent(event)
  }


}