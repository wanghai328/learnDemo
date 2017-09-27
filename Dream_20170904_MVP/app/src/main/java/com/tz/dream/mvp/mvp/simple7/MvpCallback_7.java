package com.tz.dream.mvp.mvp.simple7;

/**
 * 作者: Dream on 2017/9/4 22:28
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//抽象解绑和绑定(MvpCallback)
public interface MvpCallback_7<V extends MvpView_7, P extends MvpPresenter_7<V>> {

    //创建Presenter
    P createPresenter();

    //创建View
    V createView();

    void setPresenter(P presenter);

    P getPresneter();

    void setMvpView(V view);

    V getMvpView();
}
