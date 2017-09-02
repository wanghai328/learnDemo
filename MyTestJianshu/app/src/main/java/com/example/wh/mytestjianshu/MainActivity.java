package com.example.wh.mytestjianshu;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    private StarView starView;
    private LinearLayout lyContent;
    private Button btn1,btn2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            Log.d("123","-------run------"+starView.getStarNums() );
            // TODO Auto-generated method stub
            if(!starView.isRunning()){
                starView.start();
            }
            starView.addStars(25);
            handler.postDelayed(runnable, 200);
            if(starView.getStarNums() >= StarView.MAX_NUM)
            {
                handler.removeCallbacksAndMessages(null);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lyContent = (LinearLayout) findViewById(R.id.ly_content);

        starView = new StarView(this);
        lyContent.addView(starView);

        handler.postDelayed(runnable, 0);

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(starView.isRunning()){
                    starView.pause();
                }else{
                    starView.start();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        starView.stop();
    }
}
