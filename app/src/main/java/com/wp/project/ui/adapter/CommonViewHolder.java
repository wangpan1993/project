package com.wp.project.ui.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *  listview和gridview通用viewholder
 * Created by WangPan on 2017/12/12.
 */

public class CommonViewHolder {
    private SparseArray<View> mViews;
    public int mPosition;
    private View mConvertView;

    public CommonViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null)
            return new CommonViewHolder(context, parent, layoutId, position);
        else {
            CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);

        }
        return (T) view;
    }

    public View getmConvertView() {
        return mConvertView;
    }
}

