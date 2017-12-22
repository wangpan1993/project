package com.wp.project.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * listview和gridview通用adapter
 * <p>
 * Created by WangPan on 2017/12/12.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mDatas;
    private int layoutId;

    public CommonAdapter(Context mContext, int layoutId) {
        this.mContext = mContext;
        mDatas = new ArrayList<>();
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, layoutId, null);
            viewHolder = new CommonViewHolder(mContext, parent, layoutId, position);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CommonViewHolder) convertView.getTag();
        }
        initAdapter(viewHolder, position, getItem(position));
        return viewHolder.getmConvertView();
    }

    public void setDatas(List<T> datas, boolean isClear) {
        if (isClear) {
            mDatas.clear();
        }
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public abstract void initAdapter(CommonViewHolder viewHolder, int position, T bean);


}
