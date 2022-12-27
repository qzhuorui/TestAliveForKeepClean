package com.choryanquan.testaliveforkeepclean.defpackage;

import android.os.Build;

import java.lang.reflect.Method;

/* compiled from: MyReflection.java */
/* renamed from: pl0  reason: default package */
/* loaded from: classes2.dex */
public class MyReflection {

    public static Object getRuntime;
    public static Method setHiddenApiExemptions;
    public static int c;
    public static int d;

    static {
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Method declaredMethod = Class.class.getDeclaredMethod("forName", String.class);
                Method declaredMethod2 = Class.class.getDeclaredMethod("getDeclaredMethod", String.class,
                        Class[].class);
                Class cls = (Class) declaredMethod.invoke(null, "dalvik.system.VMRuntime");
                setHiddenApiExemptions = (Method) declaredMethod2.invoke(cls, "setHiddenApiExemptions",
                        new Class[]{String[].class});
                getRuntime = ((Method) declaredMethod2.invoke(cls, "getRuntime", null)).invoke(null, new Object[0]);
            } catch (Throwable unused) {
            }
        }
        c = -9999;
        d = -9999;
    }

    public static boolean a(String... strArr) {
        Method method;
        Object obj = getRuntime;
        if (obj == null || (method = setHiddenApiExemptions) == null) {
            return false;
        }
        try {
            method.invoke(obj, (Object[]) strArr);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean unseal() {
        return a("L");
    }
}
