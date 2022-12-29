package com.choryanquan.testaliveforkeepclean.base

import android.app.ActivityManager
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Process
import android.util.Log
import com.choryanquan.testaliveforkeepclean.NotifyUtils
import com.choryanquan.testaliveforkeepclean.PActivityManager
import com.choryanquan.testaliveforkeepclean.R
import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper
import com.llk.reflection.JJReflection
import me.weishu.reflection.Reflection


/**
 * Created by Ryan on 2022/12/16 17:59.
 * Function :
 */
class BaseApplication : Application() {

    private val handler: Handler = Handler(Looper.myLooper()!!) {
        if (it.what == 111) {
            if (null != mediaPlayer) {
                Log.d("aliveTest", "mediaPos: ${mediaPlayer.currentPosition}")
                it.target.sendEmptyMessage(111)
            }
        }
        false
    }

    private val mediaPlayer by lazy {
        val player = MediaPlayer.create(this, R.raw.novoice).apply {
            setVolume(0.0f, 0.0f)
            isLooping = true
        }
        player
    }

    private inner class broad : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            if (p0 == null || p1 == null || p1.action == null) {
                return
            }
            Log.d("aliveTest", "onReceive: app:${p1.action}")

            when (p1.action) {
                Intent.ACTION_SCREEN_ON -> {
                    PActivityManager.instance.finishActivity()
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    }
                }
                Intent.ACTION_SCREEN_OFF -> {
                    PActivityManager.instance.startPActivity(p0)
                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start()
                    }
                }
            }
        }
    }

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Log.d("aliveTest", "onCreate: App")

        if (checkIsMainProcess(this)) {

            val survivalHelper = SurvivalHelper.instance
            survivalHelper.loadSurvival(this)

            val intentFilter = IntentFilter().apply {
                addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                addAction(Intent.ACTION_SCREEN_ON)
                addAction(Intent.ACTION_SCREEN_OFF)
            }

            if (Build.VERSION.SDK_INT >= 26) {
                this.applicationContext.registerReceiver(broad(), intentFilter, RECEIVER_VISIBLE_TO_INSTANT_APPS)
            } else {
                this.applicationContext.registerReceiver(broad(), intentFilter)
            }

            NotifyUtils.showNotify(this)

        }

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        JJReflection.apiExemptions()
        Reflection.unseal(base)
    }

    private fun checkIsMainProcess(context: Context): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName: String = getCurProcessStr(context)
            if (context.packageName.equals(processName)) {
                return true
            }
        } else {
            return true
        }
        return false
    }

    private fun getCurProcessStr(context: Context): String {
        var s = ""
        val m = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (i in m.runningAppProcesses) {
            if (i.pid == Process.myPid()) {
                s = i.processName
                break
            }
        }
        return s
    }

}