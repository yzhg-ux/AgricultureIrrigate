package com.job.from.utlis.common;

import android.util.Log;

/**
 * author ${水木科技 - yzhg} on 2018/3/15.
 * <p>
 * describe()
 */

public class LogUtils {

    /**
     * DeBug开关
     */
    private static final boolean DEBUG = true;

    /**
     * 获取当前类名
     *
     * @return
     */
    private static String getClassName() {
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        String result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());
        return result;
    }

    /**
     * 统一说明:  以下日志打印  都是成对出现的 ，
     * 一个参数的，不需要传入名称，直接获取当前类名
     * 两个参数的需要手动传入名称
     *
     * @param msg
     */
    public static void w(String msg) {
        if (DEBUG) {
            Log.w(getClassName(), msg);
        }
    }

    public static void w(String name, String msg) {
        if (DEBUG) {
            Log.w(name, msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            Log.d(getClassName(), msg);
        }
    }

    public static void d(String name, String msg) {
        if (DEBUG) {
            Log.d(name, msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG) {
            Log.e(getClassName(), msg);
        }
    }

    public static void e(String name, String msg) {
        if (DEBUG) {
            Log.e(name, msg);
        }
    }


    public static void i(String msg) {
        if (DEBUG) {
            Log.i(getClassName(), msg);
        }
    }

    public static void i(String name, String msg) {
        if (DEBUG) {
            Log.i(name, msg);
        }
    }


    public static void v(String msg) {
        if (DEBUG) {
            Log.v(getClassName(), msg);
        }
    }

    public static void v(String name, String msg) {
        if (DEBUG) {
            Log.v(name, msg);
        }
    }

    ///////////////打印重大BUG//////////////////

    public static void wtf(String msg) {
        if (DEBUG) {
            Log.wtf(getClassName(), msg);
        }
    }

    public static void wtf(String name, String msg) {
        if (DEBUG) {
            Log.wtf(name, msg);
        }
    }

}















