package com.example.wh.mytestmvplearn;

import com.example.wh.mytestmvplearn.base.BasePresenter;
import com.example.wh.mytestmvplearn.utils.HttpUtils;

/**
 * Created by wh on 17/9/28.
 */

public class QueryPresenter extends BasePresenter<QueryView> {
    private QueryModel model;

    public QueryPresenter() {
        this.model = new QueryModel();
    }

    public void queryNum(String num) {
        this.model.queryNum(num, new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                if (getView() != null)
                    getView().onQueryResult(result);
            }
        });
    }


}
