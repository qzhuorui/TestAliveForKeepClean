package com.choryanquan.testaliveforkeepclean.ctcx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.defpackage.Authenticator;
import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;

/**
 * Created by Ryan on 2022/12/19 15:08.
 * Function :
 */
public final class KepCleAuthencationService extends Service {

    public Authenticator authenticator;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Authenticator authenticator1 = this.authenticator;
        if (authenticator1 != null) {
            return authenticator1.getIBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("aliveTest", "onCreate: KepCleAuthencationService");
        this.authenticator = new Authenticator(this);
        SurvivalHelper.instance.startMakeService(this);
    }
}
