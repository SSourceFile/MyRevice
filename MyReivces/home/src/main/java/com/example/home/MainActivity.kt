package com.example.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tv: TextView = findViewById<TextView>(R.id.home1);

        tv.setOnClickListener {
            var intent = Intent(this, BTestActivity::class.java);
            startActivity(intent)
        }
    }
}