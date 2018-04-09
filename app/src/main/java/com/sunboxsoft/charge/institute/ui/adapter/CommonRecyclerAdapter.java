package com.sunboxsoft.charge.institute.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<CommonRecyclerAdapter.CommonViewHolder> {
    private Context mContext;
    private int layoutId;
    private List<T> mDatas;
    private OnItemClickListener itemClickListen;
    private OnItemLongClickListener itemLongClickListen;

    public CommonRecyclerAdapter(Context mContext, int layoutId) {
        this.mContext = mContext;
        this.layoutId = layoutId;
        mDatas = new ArrayList<>();
    }

    @Override
    public CommonRecyclerAdapter.CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, layoutId, null);

        return new CommonRecyclerAdapter.CommonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CommonRecyclerAdapter.CommonViewHolder holder, final int position) {
        initAdapter(holder, holder.itemView, position);
        if (itemClickListen != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListen.OnItemClick(v, position);
                }
            });
        }
        if (itemLongClickListen != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickListen.OnItemLongClick(v, position);
                    return true;
                }
            });
        }
    }

    public void setOnItemClickListen(OnItemClickListener itemClickListen) {
        this.itemClickListen = itemClickListen;
    }

    public void setOnItemLongClickListen(OnItemLongClickListener itemLongClickListen) {
        this.itemLongClickListen = itemLongClickListen;
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<T> datas, boolean isClear) {
        if (isClear)
            mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public abstract void initAdapter(CommonViewHolder holder, View view, int position);

    private interface OnItemClickListener {
        void OnItemClick(View v, int position);
    }

    private interface OnItemLongClickListener {
        void OnItemLongClick(View v, int position);
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        private View mView;
        private SparseArray<View> mViews;


        public CommonViewHolder(View view) {
            super(view);
            this.mView = view;
            this.mViews = new SparseArray<View>();
        }

        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }
    }
}
