package com.example.pj4test

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Switch
import android.widget.Toast
import android.widget.TextView
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.util.*
import java.text.SimpleDateFormat
import kotlin.time.*


class MainActivity : AppCompatActivity(), OnClickListener {
    private val TAG = "MainActivity"

    var start : Long = 0
    var end : Long = 0
    var inter : Long = 0

    // permissions
    private val permissions = arrayOf(RECORD_AUDIO, CAMERA)
    private val PERMISSIONS_REQUEST = 0x0000001;
    var vibrator: Vibrator? = null
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_page)
        findViewById<Button>(R.id.button).setOnClickListener(this)
    }
    fun vibrate() {
        vibrator?.vibrate(VibrationEffect.createOneShot(500, 100))
    }
    private fun checkPermissions() {
        if (permissions.all{ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
            Log.d(TAG, "All Permission Granted")
        }
        else{
            requestPermissions(permissions, PERMISSIONS_REQUEST)
        }
    }
    fun switchToMain() {
        setContentView(R.layout.activity_main)
        var sw1 : Switch = findViewById(R.id.switch1)

        sw1?.setOnCheckedChangeListener{ _ , isChecked ->
            val message = if (isChecked) "TIMER ON" else "TIMER OFF"
            if (isChecked) {
                start = System.currentTimeMillis()

                val date = Date(start)
                val mFormat = SimpleDateFormat("HH:mm")
                val time = mFormat.format(date)
                val text1 : TextView = findViewById(R.id.switch1)
                text1.setText("Studying")
            }
            else {
                end = System.currentTimeMillis()
                inter = inter + end - start

                val date = Date(inter)
                val mFormat = SimpleDateFormat("mm 분 ss 초")
                val time = mFormat.format(date)
                val text1 : TextView = findViewById(R.id.switch1)

                text1.setText("Study Time : " + time)
            }
            Toast.makeText(this@MainActivity, message,
                Toast.LENGTH_SHORT).show()
        }

        checkPermissions() // check permissions
        vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun onClick(p0: View?) {
        switchToMain()
    }
}
