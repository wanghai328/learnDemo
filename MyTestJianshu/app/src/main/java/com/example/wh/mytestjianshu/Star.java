package com.example.wh.mytestjianshu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

/**
 * Created by wh on 17/9/2.
 */

public class Star {

    /**
     * x坐标、y坐标位置
     */
    float x, y;
    /**
     * 小星星旋转的角度
     */
    float rotation;
    /**
     * 小星星下落的速度
     */
    float speed;
    /**
     * 小星星旋转的速度
     */
    float rotationSpeed;
    /**
     * 小星星的宽和高
     */
    int width, height;

    /**
     * 对应的bitmap
     */
    Bitmap bitmap;

    static HashMap<Integer, Bitmap> bitmapMap = new HashMap<Integer, Bitmap>();

    /**
     * @param context
     * @param xRange 小星星在X方向上的范围
     * @param resId 资源文件Id
     * @return
     */
    static Star createStar(float xRange,int resId, Context context) {
        Star star = new Star();
        //如果十大图片则需要单独处理
        Bitmap originalBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        star.width = originalBitmap.getWidth();
        star.height = originalBitmap.getHeight();
        //初始坐标位置
        star.x = (float)Math.random() * (xRange - star.width);
        star.y = 0 - (star.height + (float)Math.random() * Util.getScreenHeight(context));
        //定义下落的速度
        star.speed = Util.dipToPX(context,50) + (float) Math.random() * Util.dipToPX(context,50);
        //旋转角度及旋转速度(根据需要修改)
        star.rotation = (float) Math.random() * 180 - 90;
        star.rotationSpeed = (float) Math.random() * 90 - 45;
        //获得bitmap对象
        star.bitmap = bitmapMap.get(resId);
        if (star.bitmap == null) {
            star.bitmap = Bitmap.createScaledBitmap(originalBitmap,
                    star.width, star.height, true);
            bitmapMap.put(resId, star.bitmap);
        }
        return star;
    }
}
