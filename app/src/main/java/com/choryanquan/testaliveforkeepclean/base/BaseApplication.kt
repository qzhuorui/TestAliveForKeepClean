package com.choryanquan.testaliveforkeepclean.base

import android.app.ActivityManager
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Process
import android.util.Log
import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper


/**
 * Created by Ryan on 2022/12/16 17:59.
 * Function :
 */
class BaseApplication : Application() {

    private inner class broad : BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("aliveTest", "onReceive: app")
        }
    }

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Log.d("aliveTest", "onCreate: App")

        val survivalHelper = SurvivalHelper.instance
        survivalHelper.loadSurvival(this)


        if (checkIsMainProcess(this)) {
            val intentFilter = IntentFilter().apply {
                addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
            }

            if (Build.VERSION.SDK_INT >= 26) {
                this.applicationContext.registerReceiver(broad(), intentFilter, RECEIVER_VISIBLE_TO_INSTANT_APPS)
            } else {
                this.applicationContext.registerReceiver(broad(), intentFilter)
            }
        }

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