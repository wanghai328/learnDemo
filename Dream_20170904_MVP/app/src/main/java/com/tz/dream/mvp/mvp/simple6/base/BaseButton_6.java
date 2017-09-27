package com.tz.dream.mvp.mvp.simple6.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * 作者: Dream on 2017/9/4 22:00
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//集成MVP设计(父类:抽象部分)
public abstract class BaseButton_6<V extends BaseView_6, P extends BasePresenter_6<V>> extends Button {

    private P presneter;
    private V view;

    public P getPresneter() {
        return presneter;
    }

    public BaseButton_6(Context context) {
        super(context);
    }

    public BaseButton_6(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.presneter == null){
            //创建P层
            this.presneter = createPresneter();
        }

        if (this.view == null){
            //创建V层
            this.view = createView();
        }
        //判定是否为空
        if (this.presneter == null){
            throw new NullPointerException("presneter不能够为空");
        }
        if (this.view == null){
            throw new NullPointerException("view不能够为空");
        }
        //绑定
        this.presneter.attachView(this.view);
    }

    //并不知道具体的P是哪一个实现类，由他的子类决定(BaseActivity子类决定具体类型)
    public abstract P createPresneter();

    //并不知道具体的V是哪一个实现类，由他的子类决定(BaseActivity子类决定具体类型)
    public abstract V createView();

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.presneter.detachView();
    }
}
