package com.choryanquan.testaliveforkeepclean.defpackage;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.AccountUtils;
import com.choryanquan.testaliveforkeepclean.ProcessUtils;
import com.choryanquan.testaliveforkeepclean.ServiceUtils;
import com.choryanquan.testaliveforkeepclean.VirtualDisplay;
import com.choryanquan.testaliveforkeepclean.job.RunningJobService;
import com.choryanquan.testaliveforkeepclean.kslk.servic.MakeService;
import com.choryanquan.testaliveforkeepclean.work.LaunchWorkder;

import java.util.Objects;

/**
 * Created by Ryan on 2022/12/19 09:53.
 * Function :
 */
public final class SurvivalHelper {

    public static final SurvivalHelper instance = new SurvivalHelper();


    public static final void createVirDis(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("aliveTest", "createVirDis: ");
                new VirtualDisplay(context.getApplicationContext()
                        , context.getApplicationContext()
                        .getSystemService(DisplayManager.class)
                        .createVirtualDisplay("lock", 10, 10, 10, null, 0)
                        .getDisplay()
                ).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void loadSurvival(Context context) {
        //check is curProgress
        if (Objects.requireNonNull(ProcessUtils.getProcessName()).equalsIgnoreCase(context.getPackageName())) {

            Log.d("aliveTest", "loadSurvival: start");

            AccountUtils.instance.startAccountSync(context);
            RunningJobService.instance.initJobScheduler(context);
            LaunchWorkder.instance.initWorkRequest(context);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    SurvivalHelper.createVirDis(context);
                }
            }, 1_000L);
        }
    }

    public final void startMakeService(Context context) {
        boolean b2 = ServiceUtils.startForeService(context, MakeService.class);
    }

}
