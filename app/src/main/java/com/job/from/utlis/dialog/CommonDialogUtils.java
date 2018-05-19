package com.job.from.utlis.dialog;

import android.content.Context;
import android.view.Gravity;

import com.job.from.R;


public class CommonDialogUtils {

    private CustomDialog mProgressDialog;

    /**
     * 创建Dialog弹框
     * @param context
     */
    public void showProgress(Context context){
        if(mProgressDialog ==null){
            mProgressDialog = new CustomDialog.Builder(context)
                    .setView(R.layout.load_dialog)
                    .setStyle(R.style.MyDialogTheme)
                    .setGravity(Gravity.CENTER)
                    .build();
        }
        if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    /**
     * 创建Dialog弹框
     * @param context
     */
    public void showProgress(Context context, String msg){
        if(mProgressDialog ==null){
            mProgressDialog = new CustomDialog.Builder(context)
                    .setView(R.layout.load_dialog)
                    .setStyle(R.style.MyDialogTheme)
                    .setGravity(Gravity.CENTER)
                    .setCancelTouchOut(false)
                    .setText(R.id.tv_load_dialog,msg)
                    .build();
        }
        if(!mProgressDialog.isShowing()){
            mProgressDialog.show();
        }
    }

    /**
     * 关闭弹框
     */
    public void dismissProgress(){
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.dismiss();
        }
    }

}
