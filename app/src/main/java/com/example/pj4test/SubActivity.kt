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
        val timerperson : TextView = findViewById(R.id.timerPerson)
        val date = Date(MainActivity().inter_person)
        val mFormat = SimpleDateFormat("mm 분 ss 초")
        val time = mFormat.format(date)
        timerperson.setText(time)

        var trans_btn : ImageButton = findViewById(R.id.transition_button)
        trans_btn.setOnClickListener {
            finish()
        }
    }
}