package com.tz.dream.mvp.mvp.simple7;

import android.os.Bundle;

/**
 * 作者: Dream on 2017/9/4 22:38
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//第二重代理->目标对象->针对的是Activity生命周期
public class MvpActivityDelegateImpl_7<V extends MvpView_7, P extends MvpPresenter_7<V>> implements MvpActivityDelegate_7<V, P> {

    private ProxyMvpCallback<V, P> proxyMvpCallback;
    private MvpCallback_7<V, P> mvpCallback;

    public MvpActivityDelegateImpl_7(MvpCallback_7<V, P> mvpCallback){
        this.mvpCallback = mvpCallback;
        if (mvpCallback == null){
            throw new NullPointerException("不能够为空");
        }
        this.proxyMvpCallback = new ProxyMvpCallback<>(mvpCallback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //绑定处理
        this.proxyMvpCallback.createPresenter();
        this.proxyMvpCallback.createView();
        this.proxyMvpCallback.attachView(this.proxyMvpCallback.getMvpView());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onRestart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        this.proxyMvpCallback.detachView();
    }

}
