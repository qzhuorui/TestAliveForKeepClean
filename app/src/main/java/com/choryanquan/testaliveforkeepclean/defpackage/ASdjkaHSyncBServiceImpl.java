package com.choryanquan.testaliveforkeepclean.defpackage;

import android.accounts.Account;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/* compiled from: ASdjkaHSyncBService.kt */
/* renamed from: t6  reason: default package */
/* loaded from: classes2.dex */
public final class ASdjkaHSyncBServiceImpl extends x40 {

    @Override // defpackage.bs
    public void startSync(FabCInter dsVar, String str, Account account, Bundle bundle) {
        Log.d("aliveTest", "startSync: B");
        if (dsVar != null) {
            try {
                dsVar.I1(new SyncResult());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override // defpackage.bs
    public void W3(FabCInter dsVar) {
        Log.d("aliveTest", "W3: B");
    }

    @Override // defpackage.bs
    public void t1(FabBInter csVar) {
        if (csVar != null) {
            Log.d("aliveTest", "t1: B");
            try {
                csVar.o3(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
