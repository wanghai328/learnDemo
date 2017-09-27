package com.tz.dream.mvp.mvp.simple1;

import com.tz.dream.mvp.utils.HttpUtils;

/**
 * 作者: Dream on 2017/9/4 21:20
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//P层
//特点：持有UI层和数据层引用
public class LoginPresenter_1 {

    //持有M层引用
    private LoginModel_1 loginModel;

    //持有UI层引用
    private LoginView_1 loginView;

    //构造方法绑定UI层
    public LoginPresenter_1(LoginView_1 loginView){
        this.loginModel = new LoginModel_1();
        this.loginView = loginView;
    }

    public void login(String username, String password){
        this.loginModel.login(username, password, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                //回调UI层->和UI进行交互
                if (loginView != null){
                    loginView.onLoginResult(result);
                }
            }
        });
    }

}
