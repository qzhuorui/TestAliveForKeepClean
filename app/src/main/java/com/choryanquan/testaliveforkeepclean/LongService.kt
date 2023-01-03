package com.choryanquan.testaliveforkeepclean

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by Ryan on 2022/12/30 18:18.
 * Function :
 */
class LongService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notify = NotifyUtils.createNotify(this)
        this.startForeground(222, notify)
        return super.onStartCommand(intent, flags, startId)
    }

}