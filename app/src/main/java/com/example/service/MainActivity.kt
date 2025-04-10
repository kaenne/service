package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var randomCharacterEditText: EditText
    private lateinit var serviceIntent: Intent
    private val broadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomCharacterEditText = findViewById(R.id.editText_randomCharacter)
        serviceIntent = Intent(this, RandomCharacterService::class.java)

        findViewById<Button>(R.id.button_start).setOnClickListener {
            startService(serviceIntent)
        }

        findViewById<Button>(R.id.button_end).setOnClickListener {
            stopService(serviceIntent)
            randomCharacterEditText.setText("")
        }

        findViewById<Button>(R.id.button_to_foreground).setOnClickListener {
            startActivity(Intent(this, ForegroundServiceActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter("my.custom.action.tag.lab8")
        registerReceiver(broadcastReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

    inner class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val data = intent?.getCharExtra("randomCharacter", '?')
            randomCharacterEditText.setText(data.toString())
        }
    }
}
