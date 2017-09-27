package com.tz.dream.mvp.mvp.simple7;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 作者: Dream on 2017/9/4 21:46
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//Activity抽象类
public abstract class MvpActivity_7<V extends MvpView_7, P extends MvpPresenter_7<V>> extends Activity implements MvpCallback_7<V, P>{

    private P presneter;
    private V view;

    //代理对象持有目标对象引用
    private MvpActivityDelegateImpl_7<V, P> delegateImpl;

    public MvpActivityDelegateImpl_7<V, P> getDelegateImpl() {
        if (delegateImpl == null){
            this.delegateImpl = new MvpActivityDelegateImpl_7<V, P>(this);
        }
        return delegateImpl;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDelegateImpl().onCreate(savedInstanceState);
    }

    @Override
    public P createPresenter() {
        return null;
    }

    @Override
    public V createView() {
        return null;
    }

    @Override
    public P getPresneter() {
        return presneter;
    }

    @Override
    public void setPresenter(P presenter) {

    }

    @Override
    public void setMvpView(V view) {

    }

    @Override
    public V getMvpView() {
        return null;
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDelegateImpl().onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getDelegateImpl().onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDelegateImpl().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getDelegateImpl().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegateImpl().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegateImpl().onDestroy();
    }
}
