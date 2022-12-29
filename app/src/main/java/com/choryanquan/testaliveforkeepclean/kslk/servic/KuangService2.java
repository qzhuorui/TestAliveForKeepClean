package com.choryanquan.testaliveforkeepclean.kslk.servic;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.AccountUtils;

/**
 * Created by Ryan on 2022/12/19 13:53.
 * Function :
 */
public final class KuangService2 extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("aliveTest", "onCreate: KuangService2");
        AccountUtils.requestSync();
    }
}
