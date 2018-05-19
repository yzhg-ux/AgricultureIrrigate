package com.job.from.ui.weight;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.job.from.R;
import com.job.from.utlis.common.Utils;


/**
 * 用一句话描述()
 */

public class ToolBarView extends LinearLayout {

    /*上下文*/
    private Context context;
    /*标题大小*/
    private int titleSize;
    /*设置左边字体大小*/
    private int leftSize;
    /*设置右边字体大小*/
    private int rightSize;
    /*设置标题*/
    private String titleText;
    /*设置左侧标题*/
    private String leftText;
    /*设置右侧的标题*/
    private String rightText;
    /*设置背景图片*/
    private int bgImage;
    /*背景颜色*/
    private int bgColor;
    /*是否显示左侧箭头,默认为true*/
    private boolean isShowLeft;
    /*是否显示左侧的文本,默认false不显示*/
    private boolean isShowLeftText;
    /*是否显示右侧的文本 默认false不显示*/
    private boolean isShowRightText;
    /*是否显示中间的文本*/
    private boolean isShowTitle;
    /*设置返回按钮图片*/
    private int backImage;
    private ImageView tvbacktoolbar;
    private TextView imbacktoolbar;
    private TextView tvtitletoolbar;
    private TextView tvcleartoolbar;
    private RelativeLayout rltoolbartitle;
    private final int DEFAULT_SIZE = 14;

    public ToolBarView(Context context) {
        super(context);
        this.context = context;
    }

    public ToolBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(LinearLayout.VERTICAL);
        initAttributes(context, attrs);
        initView(context);
    }

    public ToolBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setOrientation(LinearLayout.VERTICAL);
        initAttributes(context, attrs);
        initView(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray toolbarArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBarView);
        titleSize = toolbarArray.getDimensionPixelSize(R.styleable.ToolBarView_setTitleSize, DEFAULT_SIZE);
        leftSize = toolbarArray.getDimensionPixelSize(R.styleable.ToolBarView_setLeftSize, DEFAULT_SIZE);
        rightSize = toolbarArray.getDimensionPixelSize(R.styleable.ToolBarView_setRightSize, DEFAULT_SIZE);
        titleText = toolbarArray.getString(R.styleable.ToolBarView_setTitleText);
        leftText = toolbarArray.getString(R.styleable.ToolBarView_setLeftText);
        rightText = toolbarArray.getString(R.styleable.ToolBarView_setRightText);
        bgImage = toolbarArray.getResourceId(R.styleable.ToolBarView_setBgImage, context.getResources().getColor(R.color.color_3572D6));
        bgColor = toolbarArray.getColor(R.styleable.ToolBarView_setBgColor, context.getResources().getColor(R.color.color_207DFC));
        isShowLeft = toolbarArray.getBoolean(R.styleable.ToolBarView_isShowLeft, true);
        isShowLeftText = toolbarArray.getBoolean(R.styleable.ToolBarView_isShowLeftText, false);
        isShowRightText = toolbarArray.getBoolean(R.styleable.ToolBarView_isShowRightText, false);
        isShowTitle = toolbarArray.getBoolean(R.styleable.ToolBarView_isShowTitle, true);
        backImage = toolbarArray.getResourceId(R.styleable.ToolBarView_setLeftBackImage, R.mipmap.back_w);
        toolbarArray.recycle();
    }


    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.custom_toolbar_layout, null);
        this.rltoolbartitle = inflate.findViewById(R.id.rl_toolbar_title);
        this.tvcleartoolbar = inflate.findViewById(R.id.tv_clear_toolbar);
        this.tvtitletoolbar = inflate.findViewById(R.id.tv_title_toolbar);
        this.imbacktoolbar = inflate.findViewById(R.id.im_back_toolbar);
        this.tvbacktoolbar = inflate.findViewById(R.id.tv_back_toolbar);

        rltoolbartitle.setBackgroundColor(bgColor);

        tvbacktoolbar.setOnClickListener(v -> ((Activity) context).finish());  //点击返回
        tvbacktoolbar.setVisibility(getVisible(isShowLeft));  //设置是否显示左侧的箭头
        tvbacktoolbar.setBackgroundResource(backImage);  //设置返回箭头

        imbacktoolbar.setOnClickListener(v -> ((Activity) context).finish());  //点击返回
        imbacktoolbar.setVisibility(getVisible(isShowLeftText));  //设置是否显示左侧的文本,默认不显示
        imbacktoolbar.setText(leftText);  //设置左侧的文字
        imbacktoolbar.setTextSize(px2dip(leftSize));  //设置左侧文字的大小

        tvtitletoolbar.setVisibility(getVisible(isShowTitle)); //是否显示标题
        tvtitletoolbar.setText(titleText);  //设置标题的文本
        tvtitletoolbar.setTextSize(px2dip(titleSize));  //设置标题的大小

        tvcleartoolbar.setVisibility(getVisible(isShowRightText));
        tvcleartoolbar.setText(rightText);
        tvcleartoolbar.setTextSize(px2dip(rightSize));
        tvcleartoolbar.setOnClickListener(v -> {
            if (rightClick != null) rightClick.rightClick();
        });

        addView(inflate);
    }

    /**
     * 设置Toolbar是否隐藏,此属性不在XML中设置
     *
     * @param visible
     */
    public void setToolbarHide(boolean visible) {
        rltoolbartitle.setVisibility(getVisible(visible));
    }


    /**
     * 设置标题的字体,在java文件中动态设置
     *
     * @param resourceId
     */
    public void setTitleText(int resourceId) {
        tvtitletoolbar.setText(Utils.getString(resourceId));
    }

    public void setVisibleRight(boolean isShow) {
        tvcleartoolbar.setVisibility(getVisible(isShow));
    }

    public void setVisibleTitle(boolean isShow){
        tvtitletoolbar.setVisibility(isShow ? View.VISIBLE : View.INVISIBLE);
    }

    private int getVisible(boolean isShow) {
        return isShow ? View.VISIBLE : View.GONE;
    }

    public int dip2px(float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public float px2dip(float px) {
        float density = context.getResources().getDisplayMetrics().density;
        return px / density;
    }

    private OnToolBarClick rightClick;

    public void setOnRightClick(OnToolBarClick rightClick) {
        this.rightClick = rightClick;
    }

    public interface OnToolBarClick {
        void rightClick();
    }
}