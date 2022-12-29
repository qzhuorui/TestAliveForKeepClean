package com.choryanquan.testaliveforkeepclean.ctcx;


import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.defpackage.ASdjkaHSyncAServiceImpl2;
import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;

/**
 * Created by Ryan on 2022/12/19 15:07.
 * Function :
 */
public final class ASdjkaHSyncAService extends Service {

    private AbstractThreadedSyncAdapter abstractThreadedSyncAdapter;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (abstractThreadedSyncAdapter == null)
            return null;
        return abstractThreadedSyncAdapter.getSyncAdapterBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("aliveTest", "onCreate: ASdjkaHSyncAService");
        this.abstractThreadedSyncAdapter = new ASdjkaHSyncAServiceImpl2(getApplicationContext(), true);
        SurvivalHelper.instance.startMakeService(this);
    }
}
