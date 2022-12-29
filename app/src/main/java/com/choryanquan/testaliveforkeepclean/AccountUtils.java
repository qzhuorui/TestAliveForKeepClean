package com.choryanquan.testaliveforkeepclean;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.PeriodicSync;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.List;

/**
 * Created by Ryan on 2022/12/19 10:29.
 * Function :
 */
public final class AccountUtils {

    public static final AccountUtils instance = new AccountUtils();

    public static String author;
    public static String name;
    public static String type;
    public static Account mAccount;

    static {
        author = "com.keepclean.phoneclean.account_type.sync";
        name = "Keep Clean";
        type = "com.keepclean.phoneclean.account_type";
        mAccount = new Account(name, type);
    }

    private final void initAccountSync(Context context) {
        try {
            Log.d("aliveTest", "initAccountSync: ");

            AccountManager accountManager = AccountManager.get(context);

            Account[] byType = accountManager.getAccountsByType(type);

            if (byType == null || byType.length <= 0) {
                accountManager.addAccountExplicitly(mAccount, null, Bundle.EMPTY);//账户添加
                ContentResolver.setIsSyncable(mAccount, author, 1);//设置账户同步开启
                ContentResolver.setSyncAutomatically(mAccount, author, true);//设置账户自动同步
                ContentResolver.setMasterSyncAutomatically(true);//自动同步
            }

            if (!ContentResolver.isSyncPending(mAccount, author)) {
                requestSync();
            }

            List<PeriodicSync> syncs = ContentResolver.getPeriodicSyncs(mAccount, author);
            if (syncs == null || syncs.isEmpty()) {
                ContentResolver.addPeriodicSync(mAccount, author, Bundle.EMPTY, Build.VERSION.SDK_INT >= 24 ? 900 :
                        3600);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void requestSync() {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", true);
            bundle.putBoolean("expedited", true);
            ContentResolver.requestSync(mAccount, author, bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startAccountSync(Context context) {
        initAccountSync(context);
        new Handler(Looper.getMainLooper()).postDelayed(new RequestSyncRunnable(context), 600);
    }

    private static class RequestSyncRunnable implements Runnable {
        Context context;

        RequestSyncRunnable(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            try {
                String name = context.getString(R.string.an);
                String type = context.getString(R.string.type);
                String author = context.getString(R.string.author);

                Account account = new Account(name, type);
                (AccountManager.get(context)).addAccountExplicitly(account, null, null);
                Bundle bundle = new Bundle();
                bundle.putBoolean("expedited", true);
                bundle.putBoolean("force", true);
                ContentResolver.requestSync(account, author, bundle);
            } catch (Exception e) {
            }
        }
    }

}
