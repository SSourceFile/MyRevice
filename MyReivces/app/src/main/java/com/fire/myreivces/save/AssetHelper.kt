package com.fire.myreivces.save

import android.content.Context
import android.os.FileUtils
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.File
import java.io.FileOutputStream

class AssetHelper {

  companion object {
    private const val TAG = "AssetHelper"

    /**
     * 复制单个文件的方法
     */
    fun copyAssetSingleFile(context: Context, fileName: String, savePath: File) {
      Log.d(
        TAG,
        "copyAssetSingleFile() called with: context = $context, fileName = $fileName, savePath = $savePath"
           )
      context.assets.open(fileName).use { fis ->
        FileOutputStream(savePath).use { fos ->
          FileUtils.copy(fis, fos)
          fos.close()
          fis.close()
        }
      }

    }

    /**
     * 复制单个文件的方法
     */
    fun copyAssetSingleFileToMedia(
      context: Context,
      fileName: String,
      parcelFileDescriptor: ParcelFileDescriptor
                                  ) {
      Log.d(
        TAG,
        "copyAssetSingleFile() called with: context = $context, fileName = $fileName, parcelFileDescriptor = $parcelFileDescriptor"
           )
      context.assets.openFd(fileName).use { fis ->
        FileUtils.copy(fis.fileDescriptor, parcelFileDescriptor.fileDescriptor)
        parcelFileDescriptor.close()
        fis.close()

      }

    }

    /**
     * 复制多个文件的方法
     */
    fun copyAssetMultipleFile(context: Context, filePath: String, savePath: File) {
      Log.d(
        TAG,
        "copyAssetMultipleFile() called with: context = $context, filePath = $filePath, savePath = $savePath"
           )
      context.assets.list(filePath)?.let { fileList ->
        when (fileList.isNotEmpty()) {
          true -> {
            for (i in fileList.indices) {
              if (!savePath.exists()) savePath.mkdir()
              copyAssetMultipleFile(
                context,
                filePath + File.separator + fileList[i],
                File(savePath, fileList[i])
                                   )
            }
          }
          else -> copyAssetSingleFile(context, filePath, savePath)
        }
      }

    }
  }


}