package com.example.wh.mytestmvplearn.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by wh on 17/9/29.
 */

public abstract class BaseActivity<V extends BaseView,P extends BasePresenter<V>> extends Activity{
    public P presenter;
    private V view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(null == this.presenter){
            this.presenter = createPresenter();
        }
        if(null == this.view){
            this.view = createView();
        }
        //判定是否为空
        if (this.presenter == null){
            throw new NullPointerException("presneter不能够为空");
        }
        if (this.view == null){
            throw new NullPointerException("view不能够为空");
        }
        //绑定
        this.presenter.attachView(this.view);
    }

    public abstract P createPresenter();
    public abstract V createView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();
    }
}
