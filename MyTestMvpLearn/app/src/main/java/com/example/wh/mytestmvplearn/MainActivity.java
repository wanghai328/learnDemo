package com.example.wh.mytestmvplearn;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wh.mytestmvplearn.base.BaseActivity;

public class MainActivity extends BaseActivity<QueryView,QueryPresenter> implements View.OnClickListener, QueryView {
    private TextView numInfo;
    private EditText num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public QueryPresenter createPresenter() {
        return new QueryPresenter();
    }

    @Override
    public QueryView createView() {
        return this;
    }

    @Override
    public void onQueryResult(String result) {
        Log.d("123","Main result: "+result);
        //回调处理
        numInfo.setText(result);
    }



    private void initView() {
        num = findViewById(R.id.num);
        numInfo = findViewById(R.id.numInfo);

        findViewById(R.id.button).setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                this.presenter.queryNum(num.getText().toString());
                break;
        }
    }


}
