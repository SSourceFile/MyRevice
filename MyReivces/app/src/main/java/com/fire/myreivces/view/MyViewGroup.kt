package com.fire.myreivces.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout

class MyViewGroup : FrameLayout {
  private val TAG = "++++"
  constructor(context: Context) : super(context) {}
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}


  override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
    Log.e(TAG, "MyViewGroup === dispatchTouchEvent")
    return super.dispatchTouchEvent(ev)
  }

  override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
    Log.e(TAG, "MyViewGroup === onInterceptTouchEvent")
    return true
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    Log.e(TAG, "MyViewGroup === onTouchEvent")
    return super.onTouchEvent(event)
  }
}