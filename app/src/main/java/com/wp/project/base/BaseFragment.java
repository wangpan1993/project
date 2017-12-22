package com.wp.project.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 王攀 on 2017/2/22.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView, View.OnClickListener {
    protected Context mContext;
    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        mActivity = getActivity();
        mContext = getActivity();
        createPresenter();
        if(mPresenter!=null)
        mPresenter.attachView(this);
    }

    @SuppressWarnings("unchecked")
    protected <E> E findById(int id) {
        E view = (E) mView.findViewById(id);
        return view;
    }

    @SuppressWarnings("unchecked")
    protected <E extends View> E findById(int id, boolean isEnvent) {
        E view = (E) mView.findViewById(id);
        if (isEnvent)
            view.setOnClickListener(this);
        return view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    protected abstract void createPresenter();

    public abstract void initView();

    public abstract void initData();

    public abstract int getLayoutId();

    @Override
    public void onClick(View v) {

    }
}
