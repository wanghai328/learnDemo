package com.example.wh.mytestrecyclerview.divider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 *
 * @author wh
 * @date 17/9/11
 */

public class MyDivider extends RecyclerView.ItemDecoration{

    Paint mPaint;

    public MyDivider(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);


       /* if(parent.getChildAdapterPosition(view) != 0){
            outRect.top = 5;
        }*/
        if(parent.getChildAdapterPosition(view)% getSpanCount(parent) == 0){
            outRect.left = 5;
        }
        outRect.top = 25;
        outRect.bottom = 25;
        outRect.right = 5;
    }

    int radius = 10;

    /**
     * 绘制在itemView之下
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

      /*  int childCount = parent.getChildCount();
        for(int i=0;i<childCount;i++){
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
         //   if(index==0) continue;

            float dividerTop = view.getTop() - 5;
            float dividerBottom = view.getTop();
            float dividerLeft = view.getPaddingLeft();
            float dividerRight = parent.getWidth()-parent.getPaddingRight();
        //    c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);


            float x = view.getLeft()+view.getWidth()/2;
            float y = view.getTop()+view.getHeight()+25;


            c.drawCircle(x,y,35,mPaint);

        }*/
    }

    /**
     * 绘制在itemView之上
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

      /*  int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
//            if (index == 0) {
//                continue;
//            }

            float dividerTop = view.getTop() - 5;
            float dividerBottom = view.getTop();
            float dividerLeft = view.getPaddingLeft();
            float dividerRight = parent.getWidth() - parent.getPaddingRight();
            //    c.drawRect(dividerLeft,dividerTop,dividerRight,dividerBottom,mPaint);


            float x = view.getLeft() + view.getWidth() / 2;
            float y = view.getTop() + view.getHeight() + radius;
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            c.drawCircle(x, y, 25, paint);
        }*/
    }
    private int getSpanCount(RecyclerView parent)
    {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }
}
