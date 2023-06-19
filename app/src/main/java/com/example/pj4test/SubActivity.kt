package com.example.pj4test

import androidx.appcompat.app.AppCompatActivity
import com.example.pj4test.MainActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
//        val timerperson : TextView = findViewById(R.id.timerPerson)
        if (MainActivity().tOn != 0) {
            MainActivity().tOn = 0
            MainActivity().end_person = System.currentTimeMillis()
            MainActivity().inter_person = MainActivity().inter_person + MainActivity().end_person - MainActivity().start_person
        }

//        val date = Date(MainActivity().inter_person)
//        val mFormat = SimpleDateFormat("mm 분 ss 초")
//        val time = mFormat.format(date)
//        timerperson.setText(time)
        val studyTime = intent.getStringExtra("Study Time")

        var trans_btn : ImageButton = findViewById(R.id.transition_button)
        var study_time_view: TextView = findViewById(R.id.textView4)
        study_time_view.text = "Study Time: " + studyTime

        val personTime = intent.getStringExtra("Person Time")
        var timerperson : TextView = findViewById(R.id.timerPerson)
        timerperson.setText(personTime)

        trans_btn.setOnClickListener {
            finish()
        }
    }

//    override fun onStart() {
//        val timerperson : TextView = findViewById(R.id.timerPerson)
//        super.onStart()
//        val date = Date(MainActivity().inter_person)
//        val mFormat = SimpleDateFormat("mm 분 ss 초")
//        val time = mFormat.format(date)
//        timerperson.setText(time)
//        val studyTime = intent.getStringExtra("Study Time")
//    }
}