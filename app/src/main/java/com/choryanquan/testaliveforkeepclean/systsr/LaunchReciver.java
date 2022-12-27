package com.choryanquan.testaliveforkeepclean.systsr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;

/**
 * Created by Ryan on 2022/12/19 15:11.
 * Function :
 */
public final class LaunchReciver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context != null) {
            Log.d("aliveTest", "onReceive: LaunchReciver");
            SurvivalHelper.instance.startMakeService(context);
        }
    }
}