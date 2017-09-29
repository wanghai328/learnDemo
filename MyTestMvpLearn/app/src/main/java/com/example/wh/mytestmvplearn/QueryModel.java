package com.example.wh.mytestmvplearn;

import com.example.wh.mytestmvplearn.utils.HttpTask;
import com.example.wh.mytestmvplearn.utils.HttpUtils;


/**
 * Created by wh on 17/9/28.
 */

public class QueryModel {
    public void queryNum(String num, final HttpUtils.OnHttpResultListener onHttpResultListener){
        HttpTask httpTask = new HttpTask(onHttpResultListener);
        httpTask.execute(Constant.URL+num);
    }
}
