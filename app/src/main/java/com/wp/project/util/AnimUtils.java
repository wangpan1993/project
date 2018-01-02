package com.wp.project.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by WangPan on 2017/12/26.
 */

public class AnimUtils {

    /**
     * @param view     设置动画的控件
     * @param duration 动画持续时间
     * @param values   0为全透明，1f为不透明，值在0-1f之间变化
     */
    public Animator getAlphaAnimator(View view, long duration, float... values) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", values);
        alphaAnimator.setDuration(duration);
        return alphaAnimator;
    }

    /**
     * @param view     设置动画的控件
     * @param duration 动画持续时间
     * @param values   0为全透明，1f为不透明，值在0-1f之间变化
     */
    public void startAlphaAnimator(View view, long duration, Animator.AnimatorListener animatorListener, float... values) {
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", values);
        alphaAnimator.setDuration(duration);
        if (animatorListener != null) {
            alphaAnimator.addListener(animatorListener);
        }
        alphaAnimator.start();
    }
}
