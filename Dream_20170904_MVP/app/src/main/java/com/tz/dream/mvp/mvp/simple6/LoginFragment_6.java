package com.tz.dream.mvp.mvp.simple6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tz.dream.mvp.mvp.simple6.base.BaseFragment_6;

/**
 * 作者: Dream on 2017/9/4 22:03
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

public class LoginFragment_6 extends BaseFragment_6<LoginView_6, LoginPresenter_6> implements LoginView_6 {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresneter().login("Dream", "123456");

    }

    @Override
    public LoginPresenter_6 createPresneter() {
        return new LoginPresenter_6();
    }

    @Override
    public LoginView_6 createView() {
        return this;
    }

    @Override
    public void onLoginResult(String result) {
        Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
    }

}
