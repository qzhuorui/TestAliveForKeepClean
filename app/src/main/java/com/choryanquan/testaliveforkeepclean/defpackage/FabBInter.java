package com.choryanquan.testaliveforkeepclean.defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* compiled from: FabB.java */
/* renamed from: cs  reason: default package */
/* loaded from: classes2.dex */
public interface FabBInter extends IInterface {

    /* compiled from: FabB.java */
    /* renamed from: cs$a */
    /* loaded from: classes2.dex */
    public static abstract class FabB extends Binder implements FabBInter {

        /* compiled from: FabB.java */
        /* renamed from: cs$a$a  reason: collision with other inner class name */
        /* loaded from: classes2.dex */
        public static class C0119a implements FabBInter {
            public static FabBInter p;
            public IBinder o;

            public C0119a(IBinder iBinder) {
                this.o = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.o;
            }

            @Override // defpackage.cs
            public void o3(boolean z) {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.content.ISyncAdapterUnsyncableAccountCallback");
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.o.transact(1, obtain, null, 1) && FabB.u0() != null) {
                        FabB.u0().o3(z);
                    }
                } catch (Exception e) {

                } finally {
                    obtain.recycle();
                }
            }
        }

        public static FabBInter d0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.content" +
                    ".ISyncAdapterUnsyncableAccountCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof FabCInter)) {
                return new C0119a(iBinder);
            }
            return (FabBInter) queryLocalInterface;
        }

        public static FabBInter u0() {
            return C0119a.p;
        }
    }

    void o3(boolean z);
}
