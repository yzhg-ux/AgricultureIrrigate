package com.job.from.ui.weight;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by $(剪刀手--yzhg) on 2018/4/14 0014.
 * 用一句话描述: 事件分发机制,控制是否让子类获取到事件
 */

public class ChildClickableLinearLayout extends LinearLayout {
    //子控件是否可以接受点击事件
    private boolean childClickable = false;

    public ChildClickableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildClickableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChildClickableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ChildClickableLinearLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //返回true则拦截子控件所有点击事件，如果childclickable为true，则需返回false
        return !childClickable;
    }

    public void setChildClickable(boolean clickable) {
        childClickable = clickable;
    }
}
