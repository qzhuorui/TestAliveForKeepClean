package com.choryanquan.testaliveforkeepclean;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Ryan on 2022/12/19 10:29.
 * Function :
 */
public final class AccountUtils {

    public static final AccountUtils instance = new AccountUtils();

    public static String authatr_a;
    public static String name;
    public static String type;

    public final void initAccountSync(Context context) {
        Log.d("aliveTest", "initAccountSync: ");

        authatr_a = context.getString(R.string.authatr_a);
        name = context.getString(R.string.an);
        type = context.getString(R.string.type);
        AccountManager accountManager = AccountManager.get(context);
        Account account = new Account(name, type);
        try {
            accountManager.addAccountExplicitly(account, null, Bundle.EMPTY);//账户添加
        } catch (Exception e) {
            e.printStackTrace();
        }
        ContentResolver.setIsSyncable(account, authatr_a, 1);//设置账户同步开启
        ContentResolver.setSyncAutomatically(account, authatr_a, true);//设置账户自动同步
        ContentResolver.setMasterSyncAutomatically(true);//自动同步
        try {
            String str = authatr_a;
            Bundle bundle = Bundle.EMPTY;
            ContentResolver.removePeriodicSync(account, str, bundle);
            //设置账户自动同步 , 最后一个参数是同步周期
            ContentResolver.addPeriodicSync(account, authatr_a, bundle, Build.VERSION.SDK_INT >= 24 ? 900 : 3600);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        requestSync();
    }

    public final void requestSync() {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", true);
            ContentResolver.requestSync(new Account(name, type), authatr_a, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
