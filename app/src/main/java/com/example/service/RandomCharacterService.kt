package com.example.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlin.random.Random

class RandomCharacterService : Service() {

    private var isRunning = false
    private val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Background Service Started", Toast.LENGTH_SHORT).show()
        isRunning = true

        Thread {
            while (isRunning) {
                Thread.sleep(1000)
                val randomChar = alphabet[Random.nextInt(alphabet.size)]
                Log.d("RandomCharacterService", "Random Character: $randomChar")
                val broadcastIntent = Intent("my.custom.action.tag.lab8")
                broadcastIntent.putExtra("randomCharacter", randomChar)
                sendBroadcast(broadcastIntent)
            }
        }.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
        Toast.makeText(this, "Background Service Stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
