package com.example.wh.mytestrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wh.mytestrecyclerview.R;

import java.util.ArrayList;

/**
 * Created by wh on 17/10/23.
 */

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.MyHolder> {

    private ArrayList<String> mDatas;
    private Context context;

    public final int TYPE_NORMAL = 0;
    public final int TYPE_HEADER = 1;
    public final int TYPE_FOOTER = 2;

    private View headerView, footerView;


    public MyAdapter1(ArrayList<String> datas, Context context) {
        this.mDatas = datas;
        this.context = context;
    }

    @Override
    public MyAdapter1.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_FOOTER:
                return new MyAdapter1.MyHolder(footerView);
            case TYPE_HEADER:
                return new MyAdapter1.MyHolder(headerView);
            default:
                View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
                return new MyAdapter1.MyHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MyAdapter1.MyHolder holder, int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (headerView != null) {
                holder.tv.setText(mDatas.get(position - 1));
            } else {
                holder.tv.setText(mDatas.get(position));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (headerView != null && position == 0) {
            //如果有header的话第一个item应该加载Header
            return TYPE_HEADER;
        }
        if (footerView != null && position == getItemCount() - 1) {
            //如果有footer的话最后一个,应该加载Footer
            return TYPE_FOOTER;
        }
        return TYPE_NORMAL;
    }


    @Override
    public int getItemCount() {

        if (headerView == null && footerView == null) {
            return mDatas.size();
        } else if (headerView != null && footerView != null) {
            return mDatas.size() + 2;
        } else {
            return mDatas.size() + 1;
        }
    }

    public void addHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    public void addHeaderView(int resLayout) {
        headerView = LayoutInflater.from(context).inflate(resLayout, null);
        notifyItemInserted(0);
    }

    public void addFooterView(View footerView) {
        this.footerView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            if (itemView != headerView && itemView != footerView) {
                tv = (TextView) itemView.findViewById(R.id.tv);
            }

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_NORMAL
                            ? 1 : gridManager.getSpanCount();
                }
            });
        }
    }
}
