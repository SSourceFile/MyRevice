package com.example.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BTestActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_testa)

    var txt1 = findViewById<TextView>(R.id.test_1)
    txt1.setOnClickListener {
      var intent = Intent(this, ATestActivity::class.java);
      startActivity(intent)
    }

  }

  override fun onNewIntent(intent: Intent?) {
    super.onNewIntent(intent)
    Log.e("++++++", "我草");
  }
}