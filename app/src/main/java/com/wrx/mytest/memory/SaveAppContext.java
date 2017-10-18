package com.wrx.mytest.memory;

import android.content.Context;

/**
 * Created by wuruixiong on 2017/10/6.
 */

public class SaveAppContext {


    private static Context sContext;

    public static void setsContext(Context context) {
        sContext = context;
    }


}
