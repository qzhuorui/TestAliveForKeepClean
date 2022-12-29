package com.choryanquan.testaliveforkeepclean.defpackage;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.R;

import java.lang.reflect.Method;

/* compiled from: ASdjkaHSyncAService.kt */
/* renamed from: c  reason: default package */
/* loaded from: classes2.dex */
public final class ASdjkaHSyncAServiceImpl2 extends AbstractThreadedSyncAdapter {

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                Method forName = Class.class.getDeclaredMethod("forName", String.class);
                Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class,
                        Class[].class);
                Class<?> vmRuntimeClass = (Class<?>) forName.invoke(null, "dalvik.system.VMRuntime");
                Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
                Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass,
                        "setHiddenApiExemptions", new Class[]{String[].class});
                Object sVmRuntime = getRuntime.invoke(null);
                setHiddenApiExemptions.invoke(sVmRuntime, new Object[]{new String[]{"L"}});
            } catch (Throwable e) {
            }
        }
    }

    public ASdjkaHSyncAServiceImpl2(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
    }

    @Override
    public void onPerformSync(Account account, Bundle bundle, String s, ContentProviderClient contentProviderClient,
                              SyncResult syncResult) {
        Log.d("aliveTest", "onPerformSync: ");
        if (bundle.getBoolean("reset")) {
            syncResult.stats.numIoExceptions = 0;
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("expedited", true);
            bundle2.putBoolean("force", true);
            bundle2.putBoolean("reset", false);
            ContentResolver.requestSync(account, getContext().getString(R.string.author), bundle2);
            return;
        }
        syncResult.stats.numIoExceptions = 1;
        new Handler(Looper.getMainLooper()).postDelayed(new CCRunnableSync(account), 15_000L);
    }

    class CCRunnableSync implements Runnable {

        final Account account;

        CCRunnableSync(Account account) {
            this.account = account;
        }

        public void run() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("expedited", true);
            bundle.putBoolean("force", true);
            bundle.putBoolean("reset", true);
            ContentResolver.requestSync(this.account, getContext().getString(R.string.author), bundle);
        }
    }

}
