package com.fire.aop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

/*
* https://www.cnblogs.com/milovetingting/p/12188399.html
* 修改地址
**/

public class MainActivity extends AppCompatActivity {

  @Override
  @TimeSpeed("点击了按钮6")
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    View viewById = findViewById(R.id.clikc);
    viewById.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.e("+++++", "点击事件厂商");
        new Handler().postDelayed(new Runnable() {
          @Override
          public void run() {
            Log.e("+++++", "当前线程"+Thread.currentThread());

          }
        }, 2000);
        initTime();
      }
    });
    ClipData label = ClipData.newPlainText("Label", "");
    ClipboardManager systemService = (ClipboardManager) getBaseContext().getSystemService(getBaseContext().CLIPBOARD_SERVICE);
    systemService.setPrimaryClip(label);
  }


  private void initTime() {
    Log.e("+++++", "我进入了超时空进阶");
  }
}