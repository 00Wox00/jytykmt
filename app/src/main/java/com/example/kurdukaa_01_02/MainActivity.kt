package com.example.kurdukaa_01_02

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onStartClick(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}