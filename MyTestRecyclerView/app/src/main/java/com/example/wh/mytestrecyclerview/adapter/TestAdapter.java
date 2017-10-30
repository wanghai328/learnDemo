package com.example.wh.mytestrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wh.mytestrecyclerview.R;
import com.example.wh.mytestrecyclerview.base.BaseRecyclerViewAdapter;

import java.util.ArrayList;

/**
 *
 * @author wh
 * @date 17/10/23
 */

public class TestAdapter extends BaseRecyclerViewAdapter<String> {

    public TestAdapter(ArrayList<String> mDatas,Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder createNormalHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new TestAdapter.MyHolder(view);
    }
    @Override
    public void onBindView(RecyclerView.ViewHolder holder, int position) {
        if(headerView!=null && getItemViewType(position) == TYPE_NORMAL){
            ((TestAdapter.MyHolder)holder).tv.setText(mDatas.get(position-1));
        }
    }
    class MyHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            if(itemView != headerView && itemView != footerView){
                tv = (TextView) itemView.findViewById(R.id.tv);
            }

        }
    }



    @Override
    public RecyclerView.ViewHolder createHeaderHolder(ViewGroup parent, int viewType) {
        return new TestAdapter.MyHolder(headerView);
    }

    @Override
    public RecyclerView.ViewHolder createFooterHolder(ViewGroup parent, int viewType) {
        return new TestAdapter.MyHolder(footerView);
    }
}
