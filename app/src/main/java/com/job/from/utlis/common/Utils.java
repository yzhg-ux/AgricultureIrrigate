package com.job.from.utlis.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;


import com.job.from.R;
import com.job.from.utlis.toast.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * <p>
 * describe:
 * 全部的工具类
 * 目录:  加入工具类请从最后一行开始,如需修改请重置目录
 * {@link Utils#getString(int)}  ------   获取资源文件
 * {@link Utils#dip2px(float)}  ------   DP与PX之间的转换
 * {@link Utils#jumpSDK(int)}  ------   判断当前SDK版本
 * {@link Utils#getPackageVersionName(Context)}  ------   获取版本号信息
 * {@link Utils#encode(String)}  ------   MD5加密
 * {@link Utils#isWeixinAvilible(Context)}  ------   检测微信,QQ是否安装
 * {@link Utils#isPhoneLegal(String)}  ------   检测手机号 , 和邮箱是否符合规范
 * {@link Utils#closeKeyboardCommAct(Context)}  ------   软键盘处理
 * {@link Utils#eyeState(EditText, ImageView, boolean)}  ------操作小眼睛
 * {@link Utils#getIPAddress(Context)} ------   获取终端的IP地址
 * {@link Utils#getSystemTime()}   ------   获取当前时间
 * {@link Utils#calculateDouble(boolean, String, String)}  ------保留两位小数
 * {@link Utils#getLayoutID(int)} (Context, int)}  ------   加载View
 * {@link Utils#isInteger(String)}   ------ 判断是否是整数
 * {@link Utils#getViewHeight(View)}  ------ 测量View的高度
 * {@link Utils#getScreenSize(Context)} ------ 获取屏幕的宽度和高度
 * {@link Utils#saveImage(Context, View)} ------保存图片到相册
 * {@link Utils#getViewBp(View)}  ------ 将图片转为bitmap
 * {@link Utils#saveImageToGallery(Context, Bitmap)}  ----- 保存图片
 */

public class Utils {

    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("请在Application中初始化操作");
    }

    ///////////////////////////获取资源文件//////////////////////////////////////////////////////

    //获取字符串
    public static String getString(int id) {
        return getContext().getResources().getString(id);
    }

    //获取字符串数组
    public static String[] getStringArray(int id) {
        return getContext().getResources().getStringArray(id);
    }

    //获取图片资源
    public static Drawable getDrawable(int id) {
        return getContext().getResources().getDrawable(id);
    }

    //获取颜色
    public static int getColor(int id) {
        return getContext().getResources().getColor(id);
    }

    //获取尺寸
    public static int getDimen(int id) {
        return getContext().getResources().getDimensionPixelSize(id);
    }

    /////////////////////////////DP与PX之间的转换////////////////////////////////////////////////

    /**
     * dp与px之间的转换
     *
     * @param dip
     * @return
     */
    public static int dip2px(float dip) {
        //获取屏幕的密度
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public static float px2dip(float px) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return px / density;
    }

    ////////////////////////////////判断当前SDK版本///////////////////////////////////////////////////

    /**
     * 判断SDK版本
     *
     * @param SDKVersion
     * @return
     */
    public static boolean jumpSDK(int SDKVersion) {
        return Build.VERSION.SDK_INT >= SDKVersion;
    }


    //////////////////////////////获取版本号信息////////////////////////////////////

    /**
     * 获取当前版本号的方法
     *
     * @param context
     * @return
     */
    public static String getPackageVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context
                    .getPackageName(), 0);
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取版本号失败";
        }
    }

    public static int getPackageVersionCode(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context
                    .getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    ///////////////////////////////////MD5加密////////////////////////////////////

    /**
     * 密码的md5加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    sb.append("0");
                }
                sb.append(str);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    //////////////////////////////////////////检测微信,QQ是否安装///////////////////////////////////////

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /////////////////////////////////检测手机号 , 和邮箱是否符合规范////////////////////////////////

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147)|(166)|(199))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 邮箱格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (TextUtils.isEmpty(email))
            return false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    ///////////////////软键盘处理///////////////////////////////////////////////////

    /**
     * 普通关闭
     *
     * @param context
     */
    public static void closeKeyboardCommAct(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(INPUT_METHOD_SERVICE);
        if (((Activity) context).getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /*操作小眼睛*/
    public static boolean eyeState(EditText editText, ImageView eyeImage, boolean eye) {
        String userPsw = editText.getText().toString().trim();
        editText.setTransformationMethod(eye ? PasswordTransformationMethod.getInstance() :
                HideReturnsTransformationMethod.getInstance());
        eyeImage.setBackgroundResource(eye ? R.mipmap.icon_eye : R.mipmap.icon_eye_show);
        editText.setSelection(userPsw.length());
        return !eye;
    }


    /**
     * 获取终端的IP地址
     *
     * @param context
     * @return
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getSystemTime() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /*Double类型高精度相加,返回String*/
    public static String calculateDouble(boolean jump, String str1, String str2) {
        BigDecimal count = new BigDecimal(str1);//当前条目的钱数
        BigDecimal doubleValue = new BigDecimal(str2);//当前条目的钱数
        DecimalFormat df = new DecimalFormat("######0.00");
        if (jump)
            return df.format(count.add(doubleValue).doubleValue());
        else
            return df.format(count.subtract(doubleValue).doubleValue());
    }

    /*Double类型高精度相加,返回String*/
    public static double calculateDouble(boolean jump, double str1, double str2) {
        BigDecimal count = new BigDecimal(str1);//当前条目的钱数
        BigDecimal doubleValue = new BigDecimal(str2);//当前条目的钱数
        DecimalFormat df = new DecimalFormat("######0.00");
        if (jump)
            return Double.valueOf(df.format(count.add(doubleValue).doubleValue()));
        else
            return Double.valueOf(df.format(count.subtract(doubleValue).doubleValue()));
    }

    /*高精度乘法运算*/
    public static double calculateMultiply(double d1, int d2) {        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static double saveDouble(String money) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return Double.valueOf(df.format(Double.valueOf(money)));
    }

    public static String saveString(String money) {
        DecimalFormat df = new DecimalFormat("######0.00");
        return df.format(Double.valueOf(money));
    }

    /**
     * 加载View
     */
    public static View getLayoutID(int layoutId) {
        return LayoutInflater.from(context).inflate(layoutId, null);
    }

    /**
     * 判断是否为整数
     *
     * @param str 传入的字符串
     * @return 是整数返回true, 否则返回false
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 测量View的高度
     *
     * @param view
     * @return
     */
    public static int getViewHeight(View view) {
        final int[] viewHeight = {0};
        view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                view.getViewTreeObserver().removeOnPreDrawListener(this);
                viewHeight[0] = view.getHeight();
                return true;
            }
        });
        return viewHeight[0];
    }

    /**
     * 获取屏幕的宽度和高度
     *
     * @param context
     * @return
     */
    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

    /**
     * 保存图片到相册
     *
     * @param context
     * @param v
     */
    public static void saveImage(Context context, View v) {
        try {
            String imagePath = saveImageToGallery(context, getViewBp(v));
            LogUtils.d("------图片保存路径------" + imagePath);
            ToastUtils.showToast(Utils.getString(R.string.image_save_album));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(Utils.getString(R.string.image_save_file));
        }
    }

    /**
     * 将View转为BitMap
     *
     * @param v view
     * @return 返回bitmap
     */
    private static Bitmap getViewBp(View v) {
        if (null == v) {
            return null;
        }
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(),
                View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(
                v.getHeight(), View.MeasureSpec.EXACTLY));
        v.layout((int) v.getX(), (int) v.getY(),
                (int) v.getX() + v.getMeasuredWidth(),
                (int) v.getY() + v.getMeasuredHeight());
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache(), 0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
        v.setDrawingCacheEnabled(false);
        v.destroyDrawingCache();
        return b;
    }

    /**
     * 保存图片
     *
     * @param context
     * @param bmp
     * @return
     * @throws Exception
     */
    private static String saveImageToGallery(Context context, Bitmap bmp) throws Exception {
        File appDir = SDCardOperation.ExistSDCardMkdirs("JDS");
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        FileOutputStream fos = new FileOutputStream(file);
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        fos.flush();
        fos.close();
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
                ("file://" + file.getAbsolutePath())));
        return file.getAbsolutePath();
    }
}
