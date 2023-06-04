package com.example.pj4test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        var trans_btn : ImageButton = findViewById(R.id.transition_button)
        trans_btn.setOnClickListener {
            finish()
        }
    }
}