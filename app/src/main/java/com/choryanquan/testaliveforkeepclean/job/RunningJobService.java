package com.choryanquan.testaliveforkeepclean.job;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.work.LaunchWorkder;
import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;
import com.choryanquan.testaliveforkeepclean.ml;

/**
 * Created by Ryan on 2022/12/19 10:35.
 * Function :
 */
@SuppressLint("SpecifyJobSchedulerIdRange")
public final class RunningJobService extends JobService {

    public static final a instance = new a(null);

    /* compiled from: RunningJonbSve.kt */
    /* loaded from: classes2.dex */
    public static final class a {
        public a() {
        }

        public /* synthetic */ a(ml mlVar) {
            this();
        }

        public final void initJobScheduler(Context context) {
            try {
                Log.d("aliveTest", "initJobScheduler: ");
                Object systemService = context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
                JobInfo.Builder builder = new JobInfo.Builder(7892,
                        new ComponentName(context.getPackageName(), RunningJobService.class.getName()));
                builder.setMinimumLatency(3_000L);
                builder.setPersisted(true);
                ((JobScheduler) systemService).schedule(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("aliveTest", "onCreate: RunningJobService");
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d("aliveTest", "onStartJob: RunningJobService");
        SurvivalHelper.instance.startMakeService(this);
        LaunchWorkder.instance.initWorkRequest(this);
        return false;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("aliveTest", "onStopJob: RunningJobService");
        return false;
    }
}
