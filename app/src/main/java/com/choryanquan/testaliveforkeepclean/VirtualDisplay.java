package com.choryanquan.testaliveforkeepclean;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

/**
 * Created by Ryan on 2022/12/19 09:56.
 * Function :
 */
public final class VirtualDisplay extends Presentation {

    public static int p;

    public VirtualDisplay(Context outerContext, Display display) {
        super(outerContext, display);
    }

    public VirtualDisplay(Context outerContext, Display display, int theme) {
        super(outerContext, display, theme);
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        p++;
        setContentView(new TextView(getContext()));
    }

}
