package com.choryanquan.testaliveforkeepclean.systsr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;
import com.choryanquan.testaliveforkeepclean.PActivityManager;

/**
 * Created by Ryan on 2022/12/19 11:30.
 * Function :
 */
public final class ScRcver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            SurvivalHelper.instance.startMakeService(context);
            switch (intent.getAction()) {
                case "android.intent.action.SCREEN_ON": {
                    PActivityManager.instance.finishActivity();
                    context.sendBroadcast(new Intent("_ACTION_SCREEN_ON"));
                    break;
                }
                case "android.intent.action.SCREEN_OFF": {
                    PActivityManager.instance.startPActivity(context);
                    context.sendBroadcast(new Intent("_ACTION_SCREEN_OFF"));
                    break;
                }
            }
        }
    }
}
