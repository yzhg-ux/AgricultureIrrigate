package com.job.from.utlis.common;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.job.from.R;


public class CountDomeTime {

    private static Animation animation;

    public static CountDomeTime createTime(Context context) {
        if (animation == null)
            animation = AnimationUtils.loadAnimation(context, R.anim.animation_text);
        return new CountDomeTime();
    }

    public CountDownTimer countDomeTimer(TextView view, View view1, View view2, long duration, long interval) {
        return new CountDownTimer(duration, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                view.startAnimation(animation);
                long l = millisUntilFinished / 1000;
                view.setText((l < 10) ? "0" + l : String.valueOf(l));
            }

            @Override
            public void onFinish() {
                view.setEnabled(false);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
            }
        };
    }
}
