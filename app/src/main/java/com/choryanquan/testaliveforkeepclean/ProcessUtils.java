package com.choryanquan.testaliveforkeepclean;


import android.app.Application;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Ryan on 2022/12/19 10:27.
 * Function :
 */
public class ProcessUtils {
    public static String getProcessName() {
        Throwable th;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        Exception e;
        try {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedReader2 = new BufferedReader(new FileReader(new File("/proc/self/cmdline")));
            try {
                String trim = bufferedReader2.readLine().trim();
                try {
                    bufferedReader2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return trim;
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return "";
            }
        } catch (Exception e5) {
            e = e5;
            bufferedReader2 = null;
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            try {
                throw th;
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    private static Context mContext;

    public static Context context() {
        if (mContext == null) {
            mContext = field2Application();
            if (mContext == null) mContext = method2Application();
        }
        return mContext;
    }

    private static Application field2Application() {
        Application application = null;
        try {
            Class aClass = Class.forName("android.app.ActivityThread");
            Field field = aClass.getDeclaredField("mInitialApplication");
            field.setAccessible(true);
            Method thread = aClass.getDeclaredMethod("currentActivityThread");
            thread.setAccessible(true);
            Object o = thread.invoke(null);
            application = (Application) field.get(o);
        } catch (Exception e) {
        }
        return application;
    }

    private static Application method2Application() {
        Application application = null;
        try {
            Class aClass = Class.forName("android.app.ActivityThread");
            Method thread = aClass.getDeclaredMethod("currentActivityThread");
            thread.setAccessible(true);
            Object o = thread.invoke(null);
            Method method = aClass.getDeclaredMethod("getApplication");
            method.setAccessible(true);
            application = (Application) method.invoke(o);
        } catch (Exception e) {
        }
        return application;
    }
}
