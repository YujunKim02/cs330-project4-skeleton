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
        val studyTime = intent.getStringExtra("Study Time")

        var trans_btn : ImageButton = findViewById(R.id.transition_button)
        var study_time_view: TextView = findViewById(R.id.textView4)
        study_time_view.text = "Study Time: " + studyTime
        trans_btn.setOnClickListener {
            finish()
        }
    }
}