package com.choryanquan.testaliveforkeepclean.ctcx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;
import com.choryanquan.testaliveforkeepclean.defpackage.ASdjkaHSyncBServiceImpl;
import com.choryanquan.testaliveforkeepclean.defpackage.x40;

/**
 * Created by Ryan on 2022/12/19 15:07.
 * Function :
 */
public final class ASdjkaHSyncBService extends Service {
    public x40 abstractThreadedSyncAdapter;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        x40 x40 = this.abstractThreadedSyncAdapter;
        if (x40 != null) {
            return x40.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("aliveTest", "onCreate: ASdjkaHSyncBService");
        this.abstractThreadedSyncAdapter = new ASdjkaHSyncBServiceImpl();
        SurvivalHelper.instance.startMakeService(this);
    }
}
