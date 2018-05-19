package com.job.from.base.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.job.from.R;
import com.job.from.utlis.common.Utils;


/**
 * <p>
 * 警句:真英雄大勇无谓,好风景总在险峰
 * 概括(定义一个自定义布局,有加载中,加载失败,加载成功三种布局)
 */

public class LoadingPager extends FrameLayout {

    public static final int STATE_LOADING = 0;
    public static final int STATE_EMPTY = 1;
    public static final int STATE_SUCCESS = 2;
    public static final int STATE_ERROR = 3;
    protected LayoutInflater mInflater;
    private int mState = STATE_LOADING;//当前的状态,默认为加载状态
    private View mEmptyView;//数据为空时显示的View
    private View mErrorView;//加载错误时显示的View
    private View mContentView;//加载成功时显示的View
    private View mLoadingView;//正在加载时显示数据的View

    public LoadingPager(@NonNull Context context) {
        super(context);
        mInflater = LayoutInflater.from(getContext());
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(getContext());
    }

    public LoadingPager(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(getContext());
    }

    /**
     * 在布局完成的时候调用该方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalStateException("LoadingPager 只允许有一个子布局");
        }
        mContentView = getChildAt(0);
        mContentView.setVisibility(GONE);  //加载成功的布局 默认是不可见的
    }

    public int getStateCurrent() {
        return mState;
    }

    /**
     * 设置加载中的布局...
     */
    public void setLoadingView() {
        setLoadingView(mInflater.inflate(R.layout.base_loading_layout, this, false));
    }

    private void setLoadingView(View view) {
        removeView(mLoadingView);
        mLoadingView = view;
        //将布局添加到View中
        addStateView(view);
        updateViewState();  //切换布局状态
    }

    /**
     * 设置空布局....
     */
    public void setEmptyView(int hintText,int hintImage) {
        View view = mInflater.inflate(R.layout.load_empty_layout, this, false);
        TextView tvemptytext =  view.findViewById(R.id.tv_empty_text);
        ImageView ivloadimage =  view.findViewById(R.id.iv_load_image);
        tvemptytext.setText(Utils.getString(hintText));
        ivloadimage.setBackgroundResource(hintImage);
        setEmptyView(view);
    }

    private void setEmptyView(View view) {
        removeView(mEmptyView);
        mEmptyView = view;
        addStateView(view);
        updateViewState();
    }

    /**
     * 设置加载失败的布局.....
     */
    public void setErrorView() {
        View view = mInflater.inflate(R.layout.base_error_layout, this, false);
        setErrorView(view);
        LinearLayout ll_error_base = view.findViewById(R.id.ll_error_base);
        ll_error_base.setOnClickListener(v -> {
            if (listener != null) listener.onclick(v);
        });
    }

    private void setErrorView(View view) {
        removeView(mErrorView);
        mErrorView = view;
        addStateView(view);
        updateViewState();
    }


    /**
     * 添加布局的方法,
     *
     * @param view
     */
    private void addStateView(View view) {
        if (view != null) {
            ViewGroup.LayoutParams defaultParams = view.getLayoutParams();
            //如果默认没有LayoutParams或者不是FrameLayout.LayoutParams,重新生成一个
            if (defaultParams == null || !(defaultParams instanceof LayoutParams)) {
                defaultParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT);
            }
            LayoutParams params = (LayoutParams) defaultParams;
            params.gravity = Gravity.CENTER;//局中显示
            view.setVisibility(GONE);
            addView(view, params);
        }
    }

    /**
     * 设置当前的显示状态,
     */
    public void setState(int state) {
        switch (state) {
            case STATE_LOADING:
            case STATE_EMPTY:
            case STATE_SUCCESS:
            case STATE_ERROR:
                mState = state;
                //去更改布局显示
                updateViewState();
                break;
            default:
                throw new IllegalStateException("只允许指定 STATE_LOADING(加载中...) STATE_EMPTY(数据为空...)" +
                        " STATE_SUCCESS(加载数据成功....) STATE_ERROR(加载数据失败...) 四种状态");

        }
    }


    /**
     * 做布局的显示与隐藏,根据当前的状态
     */
    private void updateViewState() {
        if (mLoadingView != null)
            mLoadingView.setVisibility(mState == STATE_LOADING ? VISIBLE : GONE);
        if (mEmptyView != null) mEmptyView.setVisibility(mState == STATE_EMPTY ? VISIBLE : GONE);
        if (mContentView != null)
            mContentView.setVisibility(mState == STATE_SUCCESS ? VISIBLE : GONE);
        if (mErrorView != null) mErrorView.setVisibility(mState == STATE_ERROR ? VISIBLE : GONE);
    }

    private onClickListener listener;

    /**
     * 实现接口回调
     */
    public void setOnPageListener(onClickListener listener) {
        this.listener = listener;
    }


    //创建接口回调
    public interface onClickListener {
        void onclick(View view);
    }
}
