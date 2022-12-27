package com.choryanquan.testaliveforkeepclean;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.List;

/**
 * Created by Ryan on 2022/12/19 10:38.
 * Function :
 */
public class ServiceUtils {
    public static boolean isServiceRunning(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices =
                ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE);
        boolean z = false;
        if (runningServices != null) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
                if (str.equals(runningServiceInfo.service.getClassName())) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static final boolean startForeService(Context context, Class<?> cls) {
        if (ServiceUtils.isServiceRunning(context, cls.getName())) {
            return false;
        }
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                context.startForegroundService(new Intent(context, cls));
            } else {
                context.startService(new Intent(context, cls));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

}
