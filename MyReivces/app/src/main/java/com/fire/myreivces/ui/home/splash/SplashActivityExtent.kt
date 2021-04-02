package com.fire.myreivces.ui.home.splash

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File



fun qualityCompress(bitmap: Bitmap, file: File){

//  bitmap.compress()
}

public fun compressBmpFromBmp(image: Bitmap): Bitmap? {
  val baos = ByteArrayOutputStream()
  var options = 100
  image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
  while (baos.toByteArray().size / 1024 > 100) {
    baos.reset()
    options -= 10
    image.compress(Bitmap.CompressFormat.JPEG, options, baos)
  }
  val isBm = ByteArrayInputStream(baos.toByteArray())
  baos.close()
  return BitmapFactory.decodeStream(isBm, null, null)
}