package com.choryanquan.testaliveforkeepclean.defpackage;

import android.accounts.Account;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.AccountUtils;

/* compiled from: ASdjkaHSyncAService.kt */
/* renamed from: c  reason: default package */
/* loaded from: classes2.dex */
public final class ASdjkaHSyncAServiceImpl extends x40 {

    @Override // defpackage.bs
    public void startSync(FabCInter dsVar, String str, Account account, Bundle bundle) {
        Log.d("aliveTest", "startSync: A");
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                MyReflection.unseal();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (bundle != null) {
            try {
                if (bundle.getBoolean("force", false)) {
                    if (!bundle.getBoolean("ignore_backoff", false)) {
                        if (dsVar != null) {
                            dsVar.I1(new SyncResult());
                        }
                        AccountUtils.instance.requestSync();
                        return;
                    } else if (dsVar != null) {
                        dsVar.I1(SyncResult.ALREADY_IN_PROGRESS);
                        return;
                    } else {
                        return;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        if (dsVar != null) {
            dsVar.I1(new SyncResult());
        }
    }

    @Override // defpackage.bs
    public void W3(FabCInter dsVar) {
        Log.d("aliveTest", "W3: A");
        AccountUtils.instance.requestSync();
    }

    @Override // defpackage.bs
    public void t1(FabBInter csVar) {
        if (csVar != null) {
            Log.d("aliveTest", "t1: A");
            try {
                csVar.o3(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
