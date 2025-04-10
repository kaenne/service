package com.example.service

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ForegroundServiceActivity : AppCompatActivity() {

    private lateinit var serviceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreground_service)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1001)
            }
        }


        serviceIntent = Intent(this, MyForegroundService::class.java)

        findViewById<Button>(R.id.button_start_foreground).setOnClickListener {
            ContextCompat.startForegroundService(this, serviceIntent)
        }

        findViewById<Button>(R.id.button_stop_foreground).setOnClickListener {
            stopService(serviceIntent)
        }

        findViewById<Button>(R.id.button_back).setOnClickListener {
            finish()
        }
    }
}
