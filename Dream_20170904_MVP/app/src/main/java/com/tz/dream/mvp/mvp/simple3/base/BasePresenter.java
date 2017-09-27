package com.tz.dream.mvp.mvp.simple3.base;

import com.tz.dream.mvp.mvp.simple3.LoginView_3;

/**
 * 作者: Dream on 2017/9/4 21:35
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class BasePresenter {

    private LoginView_3 view;

    public LoginView_3 getView() {
        return view;
    }

    public void attachView(LoginView_3 view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }


}
