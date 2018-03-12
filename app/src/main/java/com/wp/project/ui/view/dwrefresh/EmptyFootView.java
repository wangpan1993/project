package com.wp.project.ui.view.dwrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * Created by: xudiwei
 * <p>
 * on: 2017/7/3.
 * <p>
 * 描述：空脚布局
 */

public class EmptyFootView extends FrameLayout implements ILoadMoreFoot {
    public EmptyFootView(Context context) {
        super(context);
    }

    public EmptyFootView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPullUp(int distance) {

    }

    @Override
    public void onBound() {

    }

    @Override
    public void onFingerUp(int distance) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public int footViewHeight() {
        return (int) DensityUtils.dipToPx(getContext(), 300);
    }
}
