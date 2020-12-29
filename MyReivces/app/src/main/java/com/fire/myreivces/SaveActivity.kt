package com.fire.myreivces

import android.Manifest
import android.app.Activity
import android.app.RecoverableSecurityException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fire.myreivces.save.AssetHelper
import com.fire.myreivces.save.MyDownloadCollection
import com.fire.myreivces.save.MyMediaCollection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class SaveActivity  : AppCompatActivity() {

    @RequiresApi(10000)
    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main2)
      initPermission()
      Log.d(TAG, "onCreate() called with:  = ${Build.VERSION.SDK_INT}")
      //使用Kotlin 协程方式
//      btnStorage.setOnClickListener {
        GlobalScope.launch {
          insertData()
          //updateData()
          //queryData()
          //updateOtherApp()
          //openDocument()
          //createDocument()
          //deleteImages(this@MainActivity)
        }
//      }
    }

    /**
     * 添加媒体集数据 和 应用程序专属目录下数据
     */
    private suspend fun insertData() {
      Log.d(TAG, "copyAssets() called : ### START ####")

      val dataFile = File(getExternalFilesDir(null), "data")

      val imageFile =
        File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "my_app_media_image.jpg")

      val videoFile =
        File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "my_app_media_video.mp4")

      val audioFile =
        File(getExternalFilesDir(Environment.DIRECTORY_MUSIC), "my_app_media_audio.mp3")

      val downloadFile =
        File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "my_app_download_file.pdf")

      withContext(Dispatchers.IO) {

        //插入数据到应用程序专属特定目录系统整理目录下
        AssetHelper.copyAssetSingleFile(
          this@SaveActivity,
          "my_app_media_image.jpg",
          imageFile
                                       )
        AssetHelper.copyAssetSingleFile(
          this@SaveActivity,
          "my_app_media_video.mp4",
          videoFile
                                       )
        AssetHelper.copyAssetSingleFile(
          this@SaveActivity,
          "my_app_media_audio.mp3",
          audioFile
                                       )

        AssetHelper.copyAssetSingleFile(
          this@SaveActivity,
          "my_app_download_file.pdf",
          downloadFile
                                       )

        // 插入数据到应用程序专属特定目录下自定义文件夹
        AssetHelper.copyAssetMultipleFile(this@SaveActivity, "data", dataFile)


        // 插入数据到媒体集
        MyMediaCollection().insertImageToCollection(
          this@SaveActivity,
          "my_app_media_image.jpg"
                                                   )

        MyMediaCollection().insertVideoToCollection(
          this@SaveActivity,
          "my_app_media_video.mp4"
                                                   )
        MyMediaCollection().insertAudioToCollection(
          this@SaveActivity,
          "my_app_media_audio.mp3"
                                                   )
      }
      Log.d(TAG, "copyAssets() called : #### END ####")
    }

    /**
     * 查询媒体集数据
     */
    private suspend fun queryData() {
      withContext(Dispatchers.IO) {
        MyMediaCollection().queryImageCollection(this@SaveActivity)
        MyMediaCollection().queryVideoCollection(this@SaveActivity)
        MyMediaCollection().queryAudioCollection(this@SaveActivity)
      }
    }


    /**
     * 修改媒体集数据
     */
    private suspend fun updateData() {
      withContext(Dispatchers.IO) {

        val imageId = 33
        val imageNewName = "my_app_media_image_new_name.jpg"

        val videoId = 34
        val videoNewName = "my_app_media_video_new_name.mp4"

        val audioId = 26
        val audioNewName = "my_app_media_audio_new_name.mp3"

        MyMediaCollection().updateImageCollection(
          this@SaveActivity,
          imageId.toLong(),
          imageNewName
                                                 )
        MyMediaCollection().updateVideoCollection(
          this@SaveActivity,
          videoId.toLong(),
          videoNewName
                                                 )
        MyMediaCollection().updateAudioCollection(
          this@SaveActivity,
          audioId.toLong(),
          audioNewName
                                                 )
      }
    }


    /**
     * 删除媒体集数据
     */
    private suspend fun deleteData() {
      withContext(Dispatchers.IO) {

        val imageId = 33
        val videoId = 34
        val audioId = 26

        MyMediaCollection().deleteImageCollection(this@SaveActivity, imageId.toLong())
        MyMediaCollection().deleteVideoCollection(this@SaveActivity, videoId.toLong())
        MyMediaCollection().deleteAudioCollection(this@SaveActivity, audioId.toLong())
      }
    }

    /**
     * 删除 / 编辑 其他应用程序的媒体集数据(需要申请权限)
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    private fun changeOtherApp() {
      try {
        val imageId = 37
        val imageNewName = "other_app__media_image_renamed_by_own_app.jpg"

        val videoId = 39
        val videoNewName = "other_app__media_video_renamed_by_own_app.mp4"

        val audioId = 36
        val audioNewName = "other_app__media_audio_renamed_by_own_app.mp3"

        MyMediaCollection().updateImageCollection(
          this@SaveActivity,
          imageId.toLong(),
          imageNewName
                                                 )
        MyMediaCollection().updateVideoCollection(
          this@SaveActivity,
          videoId.toLong(),
          videoNewName
                                                 )
        MyMediaCollection().updateAudioCollection(
          this@SaveActivity,
          audioId.toLong(),
          audioNewName
                                                 )

        MyMediaCollection().deleteImageCollection(this@SaveActivity, imageId.toLong())
        MyMediaCollection().deleteVideoCollection(this@SaveActivity, videoId.toLong())
        MyMediaCollection().deleteAudioCollection(this@SaveActivity, audioId.toLong())

      } catch (securityException: SecurityException) {
        // 这种方式是使用抛出一个异常的方式，弹出操作处理框，让用户处理
        // 第二种方式就是删除之前进行权限检查，参考下面的deleteImages方法
        Log.d(TAG, "updateOtherApp() called : " + securityException.message)
        val recoverableSecurityException = securityException as?
                                             RecoverableSecurityException
                                           ?: throw RuntimeException(securityException.message, securityException)

        // Android 10 上面的方法，但每次处理一个文件会弹出一个框让用户操作,不能一次性大批量
        val intentSender =
          recoverableSecurityException.userAction.actionIntent.intentSender
        intentSender?.let {

          //会弹出权限对话框，让用户是否确定要授予该APP应用程序改变其他应用创建的媒体文件权限
          ActivityCompat.startIntentSenderForResult(
            this, intentSender, 200,
            null, 0, 0, 0, null
                                                   )
        }
      }
    }



    /***
     *  删除多张图片，一次请求 友好的方式
     *
     *  createDeleteRequest (Android 11 / R 上新增的 API ) --- 永久删除指定的媒体文件
     *
     *  参考该方法 , 其他新增 createWriteRequest 、createFavoriteRequest、createTrashRequest 这几个API就不写示例了，
     *
     */

    private fun deleteImages(context: Context) {

      val uri1 = Uri.parse("content://media/external/images/media/27855")
      val uri2 = Uri.parse("content://media/external/images/media/27856")
      val uri3 = Uri.parse("content://media/external/images/media/27857")
      val uri4 = Uri.parse("content://media/external/images/media/27858")
      val uri5 = Uri.parse("content://media/external/images/media/27859")

      val uris = arrayListOf(uri1,uri2,uri3,uri4,uri5)

//      val pendingIntent = if (android.os.Build.VERSION.SDK_INT >= 30) {
//        MediaStore.createDeleteRequest(context.contentResolver, uris.filter {
//          // 使用时请求权限
//          context.checkUriPermission(
//            it,
//            Binder.getCallingPid(),
//            Binder.getCallingUid(),
//            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
//                                    ) != PackageManager.PERMISSION_GRANTED
//        })
//      }
//      else {
//        TODO("VERSION.SDK_INT < R")
//      }
//      val intentSender = pendingIntent.intentSender
//      intentSender?.let {
//        //会弹出权限对话框，让用户是否确定要授予该APP应用程序改变其他应用创建的媒体文件权限
//        ActivityCompat.startIntentSenderForResult(
//          this, intentSender, 300,
//          null, 0, 0, 0, null
//                                                 )
//      }
    }


    /**
     * 访问目录
     */
    private fun openDocumentTree() {
      MyDownloadCollection().openDirectory(this@SaveActivity, PICK_DIR)
    }

    /**
     * 访问文档
     */
    private fun openDocument() {
      MyDownloadCollection().openDocument(
        this@SaveActivity,
        "*/*",//application/pdf
        PICK_DOCUMENT
                                         )
    }

    /**
     * 创建文档
     */
    private fun createDocument() {
      MyDownloadCollection().createDocument(
        this@SaveActivity, "text/plain",
        "my_app_create.text", CREATE_DOCUMENT
                                           )
    }

    /**
     * 写入数据到文档
     */
    private fun writeDocument(uri: Uri) {
      MyDownloadCollection().writeDataToDocument(
        this,
        uri,
        "balabalabalabalabalabalabalabalabalabalabalabala"
                                                )
    }

    private fun deleteDocument(uri: Uri) {
      MyDownloadCollection().deleteDocument(this, uri)
    }


    override fun onResume() {
      super.onResume()
      Log.d(TAG, "onResume() called")
    }

    override fun onDestroy() {
      super.onDestroy()
      Log.d(TAG, "onDestroy() called")

    }

    /**
     * 初始化权限
     * READ_EXTERNAL_STORAGE
     * ACCESS_MEDIA_LOCATION   (访问图片位置元数据必须请求)
     */
    private fun initPermission() {
      Log.d(TAG, "initPermission() called")
      val permissions = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION
                               )
      for (i in permissions.indices) {
        if (ContextCompat.checkSelfPermission(
            this,
            permissions[i]
                                             ) != PackageManager.PERMISSION_GRANTED
        ) {
          ActivityCompat.requestPermissions(this, permissions, PERMISSION_CODE);
        }
      }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
      super.onActivityResult(requestCode, resultCode, data)
      if (requestCode == PICK_DOCUMENT && resultCode == Activity.RESULT_OK) {
        data?.data?.also {
          Log.d(TAG, "onActivityResult() called : PICK_DOCUMENT uri = $it")
          // 返回文件的Uri
          // 自定义下面的逻辑代码.....
          // ................
          // ................
          //示例:
          deleteDocument(it) // 删除文档
        }
      }
      if (requestCode == CREATE_DOCUMENT && resultCode == Activity.RESULT_OK) {
        data?.data?.also {
          Log.d(TAG, "onActivityResult() called : CREATE_DOCUMENT uri = $it")
          // 返回文件的Uri
          // 自定义下面的逻辑代码.....
          // ................
          // ................
          //示例:
          writeDocument(it) // 写入数据到文档
        }
      }
    }

    override fun onRequestPermissionsResult(
      requestCode: Int,
      permissions: Array<String>,
      grantResults: IntArray
                                           ) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults)

      if (requestCode == PERMISSION_CODE) {
        for (i in permissions.indices) {
          Log.d(TAG, "onRequestPermissionsResult: " + grantResults[i])
          if (grantResults[i] == -1) {
            Toast.makeText(this@SaveActivity, "${grantResults[i]}权限被拒绝", Toast.LENGTH_SHORT)
              .show()
          } else {
            Toast.makeText(this@SaveActivity, "${grantResults[i]}权限被授予", Toast.LENGTH_SHORT)
              .show()
          }
        }
      }
    }

    companion object {
      private const val TAG = "MainActivity"
      private const val PERMISSION_CODE = 999
      private const val CREATE_DOCUMENT = 2000
      private const val PICK_DOCUMENT = 1000
      private const val PICK_DIR = 3000
    }

}