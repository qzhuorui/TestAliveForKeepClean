package com.choryanquan.testaliveforkeepclean;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import com.choryanquan.testaliveforkeepclean.defpackage.SurvivalHelper;

/**
 * Created by Ryan on 2022/12/19 15:15.
 * Function :
 */
public final class ConayadtasdPracvsdier extends ContentProvider {
    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        if (context == null) {
            return true;
        }
        Log.d("aliveTest", "onCreate: ConayadtasdPracvsdier");
        SurvivalHelper.instance.loadSurvival(context);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {

        Log.d("aliveTest", "query: ConayadtasdPracvsdier-------");

        AccountUtils.instance.startAccountSync(getContext());

        if (uri == null || !uri.toString().endsWith("/directories")) {
            return null;
        }

        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"accountName", "accountType", "displayName",
                "typeResourceId", "exportSupport", "shortcutSupport", "photoSupport"});
        String packageName = getContext().getPackageName();
        String packageName2 = getContext().getPackageName();
        String packageName3 = getContext().getPackageName();
        matrixCursor.addRow(new Object[]{"documentprovider", "documentprovider", "documentprovider", 0, 1, 1, 1});
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        Log.d("aliveTest", "update: ConayadtasdPracvsdier");
        return 0;
    }
}
