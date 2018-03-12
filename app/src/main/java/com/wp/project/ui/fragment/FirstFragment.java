package com.wp.project.ui.fragment;

import android.os.Bundle;
import android.os.Message;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.base.BaseFragment;

/**
 * Created by WangPan on 2017/12/20.
 */

public class FirstFragment extends BaseFragment {
    private TextView tv_content;

    @Override
    protected void createPresenter() {

    }

    @Override
    public void initView() {
        tv_content = (TextView) findById(R.id.tv_content);

    }

    @Override
    public void initData() {
        tv_content.setText(getArguments().getString("message"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_o;
    }

    public static FirstFragment putInstance(String message) {
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onSuccess(Message message) {

    }
}
