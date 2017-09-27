package com.tz.dream.mvp.mvp.simple7;

/**
 * 作者: Dream on 2017/9/4 21:40
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//高度抽象UI层接口
public interface MvpPresenter_7<V extends MvpView_7> {

    void attachView(V view);

    void detachView();

}
