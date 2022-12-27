package com.choryanquan.testaliveforkeepclean.pl;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.choryanquan.testaliveforkeepclean.PActivityManager;

/**
 * Created by Ryan on 2022/12/19 11:35.
 * Function :
 */
public final class PlActiviity extends Activity {

    public final void checkToFinish() {
        try {
            Object systemService = getApplicationContext().getSystemService(Context.POWER_SERVICE);
            if (((PowerManager) systemService).isScreenOn()) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("aliveTest", "onCreate: PlActiviity");
        PActivityManager.instance.addPActivity(this);
        Window window = getWindow();
        window.setGravity(8388659);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = 1;
        attributes.width = 1;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        checkToFinish();
    }
}
