package com.job.from.utlis.toast;

import android.widget.Toast;

import com.job.from.utlis.common.Utils;


/**
 * author ${水木科技 - yzhg} on 2018/3/15.
 * <p>
 * describe:
 *
 *      单利Toast机制
 *
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showToast(int ResMsg) {
        if (sToast == null) {
            sToast = Toast.makeText(Utils.getContext(), Utils.getString(ResMsg), Toast.LENGTH_SHORT);
        }
        sToast.setText(ResMsg);
        sToast.show();
    }

    public static void showToast(String msg) {
        if (sToast == null) {
            sToast = Toast.makeText(Utils.getContext(), msg, Toast.LENGTH_SHORT);
        }
        sToast.setText(msg);
        sToast.show();
    }
}
