package com.tz.dream.mvp.mvp.simple3;

import com.tz.dream.mvp.utils.HttpTask;
import com.tz.dream.mvp.utils.HttpUtils;

/**
 * 作者: Dream on 2017/9/4 21:18
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//M层
public class LoginModel_3 {

    public void login(String username, String password, final HttpUtils.OnHttpResultListener onHttpResultListener) {
        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                onHttpResultListener.onResult(result);
            }
        });
        httpTask.execute("http://192.168.57.1:8080/Dream_6_19_LoginServer/LoginServlet", username, password);
    }

}
