package com.example.wh.mytestmvplearn;

import com.example.wh.mytestmvplearn.base.BaseView;

/**
 * Created by wh on 17/9/28.
 */

public interface QueryView extends BaseView{
    void onQueryResult(String result);
}
