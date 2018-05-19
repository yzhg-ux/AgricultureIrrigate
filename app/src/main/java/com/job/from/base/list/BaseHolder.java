package com.job.from.base.list;

import android.content.Context;
import android.view.View;

/**
 * 作者 剪刀手 时间 2017/1/5.
 *
 * Holder的总基类,当展示不同页面的时候继承
 */
public abstract class BaseHolder<T> {
    public View holderView;
    public Context context;

    public BaseHolder(Context context) {
        this.context = context;
        holderView = initHolderView();
        holderView.setTag(this);
    }

    public abstract View initHolderView();
    public abstract void bindData(T data,int position,int mStatePosition);

    public View getHolderView() {
        return holderView;
    }
}
