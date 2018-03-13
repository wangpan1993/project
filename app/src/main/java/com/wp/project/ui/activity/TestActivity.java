package com.wp.project.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.base.BaseActivity;
import com.wp.project.modle.JokeBean;
import com.wp.project.presenter.TestPresenter;
import com.wp.project.ui.adapter.CommonRecyclerAdapter;
import com.wp.project.ui.interfaces.TestIView;
import com.wp.project.ui.view.dwrefresh.DWRefreshLayout;

import butterknife.BindView;


public class TestActivity extends BaseActivity<TestPresenter> implements TestIView {


    @BindView(R.id.rv_test)
    RecyclerView rvTest;
    @BindView(R.id.re_test)
    DWRefreshLayout dwRefreshLayout;
    private CommonRecyclerAdapter<JokeBean.DataBean> adapter;
    private boolean isClear = true;

    private int page = 1;
    private int pagesize = 5;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        getPersimmions();
        dwRefreshLayout.setOnRefreshListener(new DWRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isClear = true;
                //TODO:下拉刷新
                mPresenter.showJoke(page, pagesize);
            }

            @Override
            public void onLoadMore() {
                page++;
                isClear = false;
                mPresenter.showJoke(page, pagesize);
                //TODO:上拉加载
            }
        });
        rvTest.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter = new CommonRecyclerAdapter<JokeBean.DataBean>(mContext, R.layout.item_test) {
            @Override
            public void initAdapter(CommonViewHolder holder, View view, int position) {
                TextView textView = holder.getView(R.id.tv_test);
                textView.setText(getItem(position).getContent());
            }
        };
        rvTest.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        mPresenter.showJoke(1, 20);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter(mContext);
    }

    @Override
    public void onShowJoke(JokeBean jokeBean) {
        adapter.setDatas(jokeBean.getData(), isClear);
        dwRefreshLayout.setRefresh(false);
    }


    @Override
    public void onSuccess(Message message) {

    }
}
