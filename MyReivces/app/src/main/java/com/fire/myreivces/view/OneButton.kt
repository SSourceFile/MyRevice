package com.fire.myreivces.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

class OneButton : AppCompatButton {

  private val TAG = "++++"
  constructor(context: Context) : super(context) {}
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

  override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

    Log.e(TAG, "OneButton === dispatchTouchEvent")
    return super.dispatchTouchEvent(event)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {

    Log.e(TAG, "OneButton === onTouchEvent")
    return super.onTouchEvent(event)
  }
}