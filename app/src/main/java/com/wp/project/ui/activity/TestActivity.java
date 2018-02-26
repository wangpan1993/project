package com.wp.project.ui.activity;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wp.project.R;
import com.wp.project.application.Contants;
import com.wp.project.base.BaseActivity;
import com.wp.project.modle.JokeBean;
import com.wp.project.presenter.TestPresenter;
import com.wp.project.ui.adapter.CommonRecyclerAdapter;


public class TestActivity extends BaseActivity<TestPresenter>{

    private RecyclerView listView;
    //    private CommonAdapter<JokeBean.DataBean> adapter;
    private CommonRecyclerAdapter<JokeBean.DataBean> adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {
        getPersimmions();
        listView = findById(R.id.listView);
        layout_title.setVisibility(View.GONE);
        listView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//      listview的
//      adapter = new CommonAdapter<JokeBean.DataBean>(mContext, android.R.layout.simple_list_item_1) {
//            @Override
//            public void initAdapter(CommonViewHolder viewHolder, int position, JokeBean.DataBean bean) {
//                TextView textView = viewHolder.getView(android.R.id.text1);
//                textView.setText(adapter.getItem(position).getContent());
//            }
//        };
        adapter = new CommonRecyclerAdapter<JokeBean.DataBean>(mContext, android.R.layout.simple_list_item_1) {
            @Override
            public void initAdapter(CommonRecyclerAdapter<JokeBean.DataBean>.CommonViewHolder holder, View view, int position) {
                TextView textView = holder.getView(android.R.id.text1);
                textView.setText(adapter.getItem(position).getContent());

            }

        };

        listView.setAdapter(adapter);
    }

    @Override
    protected void initDatas() {
        mPresenter.getJonke(Contants.BASE_URL+Contants.URL_TEST,1, 5,Contants.TEST_1);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new TestPresenter(mContext);
    }

    @Override
    public void onSuccess(Message message) {
        switch (message.what) {
            case Contants.TEST_1:
                JokeBean jokeBean = (JokeBean) message.obj;
                Log.d("TestActivity", jokeBean.getData().get(0).getContent());
                break;
            case Contants.TEST_2:
                break;
        }
    }
}
