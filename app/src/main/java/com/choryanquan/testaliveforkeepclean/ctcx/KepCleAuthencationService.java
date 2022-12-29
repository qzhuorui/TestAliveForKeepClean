package com.choryanquan.testaliveforkeepclean.ctcx;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;

/**
 * Created by Ryan on 2022/12/19 15:08.
 * Function :
 */

public class KepCleAuthencationService extends Service {

    AccountAuthenticator mAccountAuthenticator;

    @Override
    public IBinder onBind(Intent intent) {
        return mAccountAuthenticator.getIBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAccountAuthenticator = new AccountAuthenticator(this);

        SurvivalHelper.instance.startMakeService(this);

    }

    static class AccountAuthenticator extends AbstractAccountAuthenticator {

        public AccountAuthenticator(Context context) {
            super(context);
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
            return null;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType,
                                 String[] requiredFeatures, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType,
                                   Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType,
                                        Bundle options) throws NetworkErrorException {
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
            return null;
        }
    }
}

