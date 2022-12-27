package com.choryanquan.testaliveforkeepclean.defpackage;

import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

/* compiled from: FabA.java */
/* renamed from: bs  reason: default package */
/* loaded from: classes2.dex */
public interface FabAInter extends IInterface {

    /* compiled from: FabA.java */
    /* renamed from: bs$a */
    /* loaded from: classes2.dex */
    public static abstract class FabA extends Binder implements FabAInter {
        public FabA() {
            attachInterface(this, "android.content.ISyncAdapter");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            Log.d("aliveTest", "onTransact: FabAInter");
            if (i == 1) {
                parcel.enforceInterface("android.content.ISyncAdapter");
                t1(FabBInter.FabB.d0(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("android.content.ISyncAdapter");
                FabCInter d0 = FabCInter.FabC.d0(parcel.readStrongBinder());
                String readString = parcel.readString();
                Bundle bundle = null;
                Account account = parcel.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(parcel) : null;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                }
                startSync(d0, readString, account, bundle);
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface("android.content.ISyncAdapter");
                W3(FabCInter.FabC.d0(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface("android.content.ISyncAdapter");
                IBinder f = getBinder();
                parcel2.writeNoException();
                parcel2.writeStrongBinder(f);
                return true;
            } else if (i != 1598968902) {
                try {
                    return super.onTransact(i, parcel, parcel2, i2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                    return true;
                }
            } else {
                parcel2.writeString("android.content.ISyncAdapter");
                return true;
            }
        }
    }

    void startSync(FabCInter dsVar, String str, Account account, Bundle bundle);

    void W3(FabCInter dsVar);

    IBinder getBinder();

    void t1(FabBInter csVar);
}
