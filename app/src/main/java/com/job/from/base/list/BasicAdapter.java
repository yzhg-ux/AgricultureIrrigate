package com.job.from.base.list;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * 作者 剪刀手 时间 2017/1/4.
 * <p>
 * Adapter基类,抽取Adapter共有的方法.供子类去实现
 */
public abstract class BasicAdapter<T> extends BaseAdapter {

    public List<T> list;
    public Context context;
    private TextView tv_bank_id;
    private int mStatePosition = 0;

    public BasicAdapter(Context context, List<T> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder<T> holderView;
        if (convertView == null) {
            holderView = getHolder(position);
        } else {
            holderView = (BaseHolder) convertView.getTag();
        }

        //绑定数据的操作
        holderView.bindData(list.get(position), position,mStatePosition);

        //一开始缩小
     /*   ViewHelper.setScaleX(holderView.getHolderView(), 0.9f);
        ViewHelper.setScaleY(holderView.getHolderView(), 0.9f);
        //执行放大动画
        ViewPropertyAnimator.animate(holderView.getHolderView()).scaleX(1.0f).setInterpolator(new
                OvershootInterpolator()).setDuration(400).start();
        ViewPropertyAnimator.animate(holderView.getHolderView()).scaleY(1.0f).setInterpolator(new
                OvershootInterpolator()).setDuration(400).start();*/

        return holderView.getHolderView();
    }


    public void setRadio(int statePosition){
        if(mStatePosition != statePosition){
            mStatePosition = statePosition;
            notifyDataSetChanged();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public abstract BaseHolder<T> getHolder(int position);
}
