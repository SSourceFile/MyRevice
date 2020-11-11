package com.fire.myreivces.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class MyText : AppCompatTextView {

  private val TAG = "++++"
  constructor(context: Context) : super(context) {}
  constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

  override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
    Log.e(TAG, "myText  ======   dispatchTouchEvent")
    return super.dispatchTouchEvent(event)
  }

  override fun onTouchEvent(event: MotionEvent?): Boolean {

    when(event?.action){
      MotionEvent.ACTION_DOWN->{
        Log.e(TAG, "myText  ======  down   onTouchEvent")
      }

      MotionEvent.ACTION_UP->{
        Log.e(TAG, "myText  ======  UP  onTouchEvent")
      }
    }
    return super.onTouchEvent(event)
  }
}