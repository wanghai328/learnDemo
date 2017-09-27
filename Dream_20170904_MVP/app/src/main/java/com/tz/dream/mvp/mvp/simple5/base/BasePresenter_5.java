package com.tz.dream.mvp.mvp.simple5.base;

/**
 * 作者: Dream on 2017/9/4 21:35
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class BasePresenter_5<V extends BaseView_5> {

    private V view;

    public V getView() {
        return view;
    }

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }


}
