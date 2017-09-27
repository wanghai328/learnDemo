package com.tz.dream.mvp.mvp.simple6.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tz.dream.mvp.mvp.simple5.base.BasePresenter_5;
import com.tz.dream.mvp.mvp.simple5.base.BaseView_5;

/**
 * 作者: Dream on 2017/9/4 21:46
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//Activity抽象类
public abstract class BaseActivity_6<V extends BaseView_5, P extends BasePresenter_5<V>> extends Activity {

    private P presneter;
    private V view;

    public P getPresneter() {
        return presneter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected void onDestroy() {
        super.onDestroy();
        this.presneter.detachView();
    }

}
