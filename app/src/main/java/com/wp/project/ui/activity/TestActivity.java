package com.wp.project.ui.activity;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.base.BaseActivity;
import com.wp.project.base.BasePresenter;
import com.wp.project.modle.JokeBean;
import com.wp.project.presenter.TestPresenter;
import com.wp.project.ui.adapter.CommonRecyclerAdapter;
import com.wp.project.ui.interfaces.TestIView;
import com.wp.project.ui.view.dwrefresh.DWRefreshLayout;

import butterknife.BindView;


public class TestActivity extends BaseActivity<TestPresenter> implements TestIView {


//    @BindView(R.id.rv_test)
//    RecyclerView rvTest;
//    @BindView(R.id.re_test)
//    DWRefreshLayout reTest;
//    private CommonRecyclerAdapter<JokeBean.DataBean> adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    public void testClick(View view) {
        mPresenter.getJonke2("", new BasePresenter.CallBack<JokeBean>() {
            @Override
            public void onSuccess(JokeBean jokeBean) {

            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    protected void initView() {
        getPersimmions();
//        reTest.setOnRefreshListener(new DWRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//
//            @Override
//            public void onLoadMore() {
//
//            }
//        });
//        rvTest.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        adapter = new CommonRecyclerAdapter<JokeBean.DataBean>(mContext, R.layout.item_test) {
//            @Override
//            public void initAdapter(CommonViewHolder holder, View view, int position) {
//                TextView textView = holder.getView(R.id.tv_test);
//                textView.setText(getItem(position).getContent());
//            }
//        };
//        rvTest.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
//        mPresenter.getJonke2();
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter(mContext);
    }

    @Override
    public void onShowJoke(JokeBean jokeBean) {
//        adapter.setDatas(jokeBean.getData(), true);
    }


    @Override
    public void onSuccess(Message message) {

    }
}
