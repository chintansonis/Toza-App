package com.app.toza.helper;

import android.content.Context;

public class PrefUtils {


    public static void setLogin(Context ctx, boolean val){
        Prefs.with(ctx).save("isLogin",val);
    }
    public static boolean isLogin(Context ctx){
        boolean val = Prefs.with(ctx).getBoolean("isLogin",false);
        return val;
    }

}
