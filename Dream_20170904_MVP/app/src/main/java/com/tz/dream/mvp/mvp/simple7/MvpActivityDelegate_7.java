package com.tz.dream.mvp.mvp.simple7;

import android.os.Bundle;

/**
 * 作者: Dream on 2017/9/4 22:35
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//第二重代理->目标接口->针对Activity生命周期进行代理
public interface MvpActivityDelegate_7<V extends MvpView_7, P extends MvpPresenter_7<V>> {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onPause();

    void onResume();

    void onRestart();

    void onStop();

    void onDestroy();

}
