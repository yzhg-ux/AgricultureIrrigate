package com.job.from.utlis.db;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * author ${剪刀手 - yzhg} on 2018/3/16.
 * <p>
 * describe:
 * 数据储存 -- 储存到SP
 * <p>
 * 每个工具,都是成对出现,有存入和取出
 */

public class PrefLoginUtils {

    /*存储登录,注册的数据,按需求,一般清除数据时 不需要清除此项数据*/
    private static final String SP_PREFS_LOGIN = "sp_login";


    /////////////////////////储存String 类型的数据/////////////////////////////////////

    public static void putString(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        return pref.getString(key, defaultValue);
    }

    //////////////////////储存Int类型的数据/////////////////////////////////////////////

    public static void putInt(Context context, String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        pref.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        return pref.getInt(key, defaultValue);
    }

    //////////////////////储存Boolean类型的数据/////////////////////////////////////////////

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        pref.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        return pref.getBoolean(key, defaultValue);
    }


    /////////////////////////////////清除SP中的数据///////////////////////////////////////////////
    public static boolean clearSp(Context context) {
        SharedPreferences clearSp = context.getSharedPreferences(SP_PREFS_LOGIN, Context.MODE_PRIVATE);
        return clearSp != null && clearSp.edit().clear().commit();
    }

}

















