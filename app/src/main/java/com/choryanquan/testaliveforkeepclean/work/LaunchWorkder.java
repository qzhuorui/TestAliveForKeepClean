package com.choryanquan.testaliveforkeepclean.work;

import android.content.Context;
import android.util.Log;

import androidx.work.Constraints;
import androidx.work.ListenableWorker;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;
import com.choryanquan.testaliveforkeepclean.ml;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryan on 2022/12/19 10:41.
 * Function :
 */
public final class LaunchWorkder extends Worker {

    public static final a instance = new a(null);

    /* compiled from: LaunchWorkder.kt */
    /* loaded from: classes2.dex */
    public static final class a {
        public a() {
        }

        public a(ml mlVar) {
            this();
        }

        public void initWorkRequest(Context context) {
            Log.d("aliveTest", "initWorkRequest: ");
            Constraints build =
                    new Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED).setRequiresBatteryNotLow(true).build();
            PeriodicWorkRequest build2 =
                    new PeriodicWorkRequest.Builder(LaunchWorkder.class, 15, TimeUnit.MINUTES).setConstraints(build).build();
            try {
                WorkManager.getInstance(context).enqueue(build2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LaunchWorkder(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
    }

    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        Log.d("aliveTest", "doWork: LaunchWorkder");
        SurvivalHelper ekVar = SurvivalHelper.instance;
        Context applicationContext = getApplicationContext();
        ekVar.startMakeService(applicationContext);
        ListenableWorker.Result success = ListenableWorker.Result.success();
        return success;
    }
}
