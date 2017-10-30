package com.example.wh.mytestrecyclerview;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wh.mytestrecyclerview.adapter.MyAdapter;
import com.example.wh.mytestrecyclerview.adapter.TestAdapter;
import com.example.wh.mytestrecyclerview.divider.DividerGrideItemDecoration;
import com.example.wh.mytestrecyclerview.divider.MyDivider;

import java.util.ArrayList;
import java.util.Collections;

import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutRightItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutTopItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideScaleInOutRightItemAnimator;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private ArrayList<String> datas ;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
    //    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
    //    MyAdapter1 adapter = new MyAdapter1(datas,this);
    //    MyAdapter adapter = new MyAdapter(datas,this);
        TestAdapter adapter = new TestAdapter(datas,this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new MyDivider());

        View header = LayoutInflater.from(this).inflate(R.layout.header,null);
        ((TextView)header.findViewById(R.id.headerText)).setText("我是header");
        adapter.addHeaderView(header);
        View footer = LayoutInflater.from(this).inflate(R.layout.header,null);
        ((TextView)footer.findViewById(R.id.headerText)).setText("我是footer");
        adapter.addFooterView(footer);


  //      recyclerView.setLayoutManager(new GridLayoutManager(this,3));
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
  //     recyclerView.setAdapter(adapter);

        //分割线
//      recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
 //       recyclerView.addItemDecoration(new DividerGrideItemDecoration(this));
//         recyclerView.addItemDecoration(new MyDivider());

        //动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setItemAnimator(new ScaleInOutItemAnimator(recyclerView));
//        recyclerView.setItemAnimator(new SlideInOutRightItemAnimator(recyclerView));
//        recyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(recyclerView));
//        recyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(recyclerView));
//        recyclerView.setItemAnimator(new SlideInOutTopItemAnimator(recyclerView));
 //       recyclerView.setItemAnimator(new SlideScaleInOutRightItemAnimator(recyclerView));



//        recyclerView.getItemAnimator().setAddDuration(1000);
//        recyclerView.getItemAnimator().setRemoveDuration(1000);


 //       setStatus();

    }

    private void initDatas(){
        datas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            datas.add("" + (char) i);
        }
    }
    /*
    private void setStatus(){
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

                if(recyclerView.getLayoutManager() instanceof GridLayoutManager){
                   *//* int dragFlag = ItemTouchHelper.UP
                            | ItemTouchHelper.DOWN
                            | ItemTouchHelper.LEFT
                            | ItemTouchHelper.RIGHT;
                    int swipFlags = 0;*//*
                    int dragFlag = 0;
                    int swipFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlag,swipFlags);
                }else{
                    int dragFlag = ItemTouchHelper.UP |ItemTouchHelper.DOWN;
                    int swipFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                    return makeMovementFlags(dragFlag,swipFlags);
                }

            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                if(fromPosition < toPosition){
                    for(int i = fromPosition;i<toPosition;i++){
                        Collections.swap(datas,i,i+1);
                    }
                }else{
                    for(int i = fromPosition;i>toPosition;i--){
                        Collections.swap(datas,i,i-1);
                    }
                }
                adapter.notifyItemMoved(fromPosition,toPosition);
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                int adapterPosition = viewHolder.getAdapterPosition();
                Log.d("123","adapterPosition: "+adapterPosition);
                adapter.notifyItemRemoved(adapterPosition);
                datas.remove(adapterPosition);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    */
}
