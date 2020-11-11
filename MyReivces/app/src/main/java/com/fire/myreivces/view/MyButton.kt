package com.fire.myreivces.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class MyButton : AppCompatButton{

  private val TAG = "++++"
  constructor(context:Context) : super(context)
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
//  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}

  override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

    Log.e(TAG, "MyButton === dispatchTouchEvent")

    return super.dispatchTouchEvent(event)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {

    Log.e(TAG, "MyButton === onTouchEvent")

    return super.onTouchEvent(event)
  }

}