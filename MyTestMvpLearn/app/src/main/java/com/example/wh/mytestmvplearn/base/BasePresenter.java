package com.example.wh.mytestmvplearn.base;

/**
 * Created by wh on 17/9/29.
 */

public class BasePresenter <V extends BaseView>{
    private V view;
    public V getView(){
        return view;
    }
    public void attachView(V v){
        this.view = v;
    }
    public void detachView(){
        this.view = null;
    }
}
