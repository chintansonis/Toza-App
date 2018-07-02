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


    public static void setLanguage(Context ctx, String val){
        Prefs.with(ctx).save("Language",val);
    }
    public static String isLanguageSet(Context ctx){
        String val = Prefs.with(ctx).getString("Language","");
        return val;
    }

}
