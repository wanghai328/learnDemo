package com.example.wh.mytestrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wh.mytestrecyclerview.R;

import java.util.ArrayList;

/**
 * Created by wh on 17/9/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    private ArrayList<String> datas;
    private Context context;

    public MyAdapter(ArrayList<String> datas,Context context){
        this.datas = datas;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//        TextView tv = (TextView) view.findViewById(R.id.tv);
//        ViewGroup.LayoutParams params = tv.getLayoutParams();
//        params.height = (int) (100+Math.random()*100);
//        tv.setLayoutParams(params);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    public void addData(int position) {
        datas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
