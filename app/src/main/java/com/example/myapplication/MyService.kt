package com.example.myapplication

import android.app.ForegroundServiceTypeException
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.ServiceCompat


class MyService : Service() {

    private var player: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder {
        return Binder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("RRR","onStartCommand()")
        // Notification message
        NotificationHelper.createNotificationChannel(this)
        startForeground(1,NotificationHelper.buildNotification(this))
        player?.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("RRR","onCreate()")
        player = MediaPlayer.create(this, R.raw.betkhoven)
        player?.isLooping = false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("RRR","onDestroy()")
        player?.stop()
    }

}