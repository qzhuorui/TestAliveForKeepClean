package com.choryanquan.testaliveforkeepclean.defpackage;

import android.content.SyncResult;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: FabC.java */
/* renamed from: ds  reason: default package */
/* loaded from: classes2.dex */
public interface FabCInter extends IInterface {

    /* compiled from: FabC.java */
    /* renamed from: ds$a */
    /* loaded from: classes2.dex */
    public static abstract class FabC extends Binder implements FabCInter {

        /* compiled from: FabC.java */
        /* renamed from: ds$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0121a implements FabCInter {
            public static FabCInter p;
            public IBinder o;

            public C0121a(IBinder iBinder) {
                this.o = iBinder;
            }

            @Override // defpackage.ds
            public void I1(SyncResult syncResult) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.ISyncContext");
                    if (syncResult != null) {
                        obtain.writeInt(1);
                        syncResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    try {
                        if (this.o.transact(2, obtain, obtain2, 0) || FabC.u0() == null) {
                            obtain2.readException();
                        } else {
                            FabC.u0().I1(syncResult);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.o;
            }
        }

        public static FabCInter d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.content.ISyncContext");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof FabCInter)) {
                return new C0121a(iBinder);
            }
            return (FabCInter) queryLocalInterface;
        }

        public static FabCInter u0() {
            return C0121a.p;
        }
    }

    void I1(SyncResult syncResult);
}
