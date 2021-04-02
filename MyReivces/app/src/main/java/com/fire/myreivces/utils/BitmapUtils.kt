package com.fire.myreivces.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapUtils {

  fun disposeBitmap(width: Int, height: Int, resource: Resources, id: Int, hasAlpha: Boolean, bitmap: Bitmap?):Bitmap {

    //获取bitmap的配置
    var option = BitmapFactory.Options();

    //开启开关
    option.inJustDecodeBounds = true

    BitmapFactory.decodeResource(resource, id, option)

    var w = option.outWidth;
    var h = option.outHeight
    //设置缩放比例
    option.inSampleSize = calcuteInSimple(w, h, width, height)
    if(!hasAlpha){
      option.inPreferredConfig = Bitmap.Config.RGB_565;
    }
    //关闭开关
    option.inJustDecodeBounds = false;

    option.inMutable = true
    option.inBitmap = bitmap
    return BitmapFactory.decodeResource(resource, id, option);
  }

  private fun calcuteInSimple(w: Int, h: Int, width: Int, height: Int): Int {
    var inSampleSize = 1;
    if(w > width && h > height){
      inSampleSize = 2;
      while ((w/inSampleSize > width) && h/inSampleSize > height){
        inSampleSize *= 2
      }
    }
    return inSampleSize;
  }
}