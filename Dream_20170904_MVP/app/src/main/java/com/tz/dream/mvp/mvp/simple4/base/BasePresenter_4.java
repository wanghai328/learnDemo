package com.tz.dream.mvp.mvp.simple4.base;

/**
 * 作者: Dream on 2017/9/4 21:35
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class BasePresenter_4<V extends BaseView_4> {

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
