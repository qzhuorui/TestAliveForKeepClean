package com.choryanquan.testaliveforkeepclean;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;


import com.choryanquan.testaliveforkeepclean.pl.PlActiviity;

import java.lang.ref.WeakReference;

/**
 * Created by Ryan on 2022/12/19 11:33.
 * Function :
 */
public final class PActivityManager {
    public static final PActivityManager instance = new PActivityManager();
    public static WeakReference<Activity> b;

    public final void finishActivity() {
        Activity activity;
        WeakReference<Activity> weakReference = b;
        if (!(weakReference == null || (activity = weakReference.get()) == null)) {
            activity.finish();
        }
        b = null;
    }

    public final void startPActivity(Context context) {
        Intent intent = new Intent(context, PlActiviity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public final void addPActivity(Activity activity) {
        b = new WeakReference<>(activity);
    }
}
