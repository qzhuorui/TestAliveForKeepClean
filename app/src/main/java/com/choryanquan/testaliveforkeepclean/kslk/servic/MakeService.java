package com.choryanquan.testaliveforkeepclean.kslk.servic;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.choryanquan.testaliveforkeepclean.R;
import com.choryanquan.testaliveforkeepclean.systsr.ScRcver;

/**
 * Created by Ryan on 2022/12/19 10:51.
 * Function :
 */
public final class MakeService extends Service {
    public MediaPlayer mediaPlayer;
    public boolean screenOn;

    /* compiled from: MakeService.kt */
    /* loaded from: classes2.dex */
    public final class MyIntentReceiver extends BroadcastReceiver {
        /* JADX WARN: Incorrect args count in method signature: ()V */
        public MyIntentReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && context != null) {
                switch (intent.getAction()) {
                    case "_ACTION_SCREEN_OFF": {
                        MakeService.this.screenOn = false;
                        MakeService.this.startVoice();
                        break;
                    }
                    case "_ACTION_SCREEN_ON": {
                        MakeService.this.screenOn = true;
                        MakeService.this.pauseVoice();
                        break;
                    }
                }
            }
        }
    }

    public static final void stopService(MakeService makeService) {
        makeService.stopForeground(true);
    }

    public static final boolean l(MediaPlayer mediaPlayer, int i, int i2) {
        return false;
    }

    public static final void checkCanStartVoice(MakeService makeService, MediaPlayer mediaPlayer) {
        if (!makeService.screenOn) {
            makeService.startVoice();
        }
    }

    public final void createNotify() {
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel("m", "m",
                    NotificationManager.IMPORTANCE_NONE);
            notificationChannel.enableVibration(false);
            notificationChannel.enableLights(false);
            notificationChannel.setShowBadge(false);
            notificationChannel.setSound(null, null);
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, "m").setWhen(System.currentTimeMillis());
            int i = R.drawable.ic_logo;
            Notification build =
                    builder.setSmallIcon(i).setLargeIcon(BitmapFactory.decodeResource(getResources(), i)).setContent(new RemoteViews(getPackageName(), R.layout.out_layout)).build();
            startForeground(54646, build);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override // java.lang.Runnable
                public final void run() {
                    MakeService.stopService(MakeService.this);
                }
            }, 500);
        }
    }

    public final void pauseVoice() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            Log.d("aliveTest", "pauseVoice: ");
            mediaPlayer.pause();
        }
    }

    public final void startVoice() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            Log.d("aliveTest", "startVoice: ");
            mediaPlayer.start();
        }
    }

    public final void initReceiver() {
        ScRcver scRcver = new ScRcver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        registerReceiver(scRcver, intentFilter);

        Object systemService = getApplicationContext().getSystemService(Context.POWER_SERVICE);
        this.screenOn = ((PowerManager) systemService).isScreenOn();
        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.novoice);

            mediaPlayer.setVolume(0.0f, 0.0f);

            mediaPlayer.setLooping(true);

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    return MakeService.l(mediaPlayer, i, i1);
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public final void onCompletion(MediaPlayer mediaPlayer4) {
                    MakeService.checkCanStartVoice(MakeService.this, mediaPlayer4);
                }
            });

            if (!this.screenOn) {
                startVoice();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        MyIntentReceiver myIntentReceiver = new MyIntentReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("_ACTION_SCREEN_OFF");
        intentFilter2.addAction("_ACTION_SCREEN_ON");
        registerReceiver(myIntentReceiver, intentFilter2);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d("aliveTest", "onCreate: MakeService");
        createNotify();
        try {
            Intent intent = new Intent();
            intent.setClassName(getPackageName(), KuangService1.class.getName());
            startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Intent intent2 = new Intent();
            intent2.setClassName(getPackageName(), KuangService2.class.getName());
            startService(intent2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        initReceiver();
    }
}
