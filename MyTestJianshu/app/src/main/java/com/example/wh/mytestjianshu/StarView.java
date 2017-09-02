package com.example.wh.mytestjianshu;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by wh on 17/9/2.
 */

public class StarView extends View {

    private Context context;
    /**
     * 小星星数目的最大值
     */
    public static final int MAX_NUM = 100;
    /**
     * 小星星的数目
     */
    private int starNums = 0;
    ArrayList<Star> stars = new ArrayList<>();
    ValueAnimator animator;
    long startTime, prevTime;
    Matrix matrix = new Matrix();
    //小星星图片的资源文件
    int[] picRes = {R.drawable.gift_25,R.drawable.gift_10,R.drawable.gift_18,R.drawable.gift_11,R.drawable.gift_24,R.drawable.gift_11};

    public StarView(Context context) {
        super(context);
        this.context = context;

        //初始化animator
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(500);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                Log.d("123","--------onAnimationUpdate--------");

                long nowTime = System.currentTimeMillis();
                float secs = (nowTime - prevTime) / 1000f;
                prevTime = nowTime;
                for (int i = 0; i < starNums; ++i) {
                    Star star = stars.get(i);
                    star.y += (star.speed * secs);
                    if (star.y > getHeight()) {
                        star.y = 0 - star.height;
                    }
                    star.rotation = star.rotation + (star.rotationSpeed * secs);
                }
                    invalidate();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // Reset list of droidflakes, then restart it with 8 flakes
        stars.clear();
        starNums = 0;
        addStars(0);
        animator.cancel();
        startTime = System.currentTimeMillis();
        prevTime = startTime;
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("123","--------onDraw--------");

        for (int i = 0; i < starNums; ++i) {
            Star flake = stars.get(i);
            matrix.setTranslate(-flake.width/2, -flake.height/2);
            matrix.postRotate(flake.rotation);
            matrix.postTranslate(flake.width/2 + flake.x, flake.height/2 + flake.y);
            canvas.drawBitmap(flake.bitmap, matrix, null);
        }
    }

    /**
     * 添加小星星
     * @param quantity
     */
    public void addStars(int quantity) {
        if(starNums <= MAX_NUM){
            for (int i = 0; i < quantity; ++i) {
                stars.add(Star.createStar(getWidth(),picRes[i%picRes.length],context));
            }
            starNums += quantity;
        }
    }

    /**
     * 暂停动画
     */
    public void pause() {
        if(animator.isRunning())
            animator.cancel();
    }

    /**
     * 开始动画
     */
    public void start() {
        startTime = System.currentTimeMillis();
        prevTime = startTime;
        if(!animator.isRunning())
            animator.start();
    }

    public void stop(){
        if(animator.isRunning())
            animator.cancel();
        animator.removeAllUpdateListeners();
        animator = null;
    }

    public boolean isRunning(){
        if(animator==null){
            return false;
        }
        return animator.isRunning();
    }
    public int getStarNums() {

        return starNums;
    }
}
