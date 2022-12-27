package com.choryanquan.testaliveforkeepclean.kslk;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;

/**
 * Created by Ryan on 2022/12/19 15:09.
 * Function :
 */
public final class KeepCleanHookInstrumentation extends Instrumentation {

    @Override // android.app.Instrumentation
    public void callApplicationOnCreate(Application application) {
        super.callApplicationOnCreate(application);
    }

    @Override // android.app.Instrumentation
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("aliveTest", "onCreate: KeepCleanHookInstrumentation");
        SurvivalHelper ekVar = SurvivalHelper.instance;
        Context context = getContext();
        ekVar.startMakeService(context);
    }
}
