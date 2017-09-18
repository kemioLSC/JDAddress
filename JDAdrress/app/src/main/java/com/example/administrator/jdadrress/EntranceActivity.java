package com.example.administrator.jdadrress;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        //去掉ActionBar
        getSupportActionBar().hide();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedUtils.isFirstRun(EntranceActivity.this)) {
                    Intent intent = new Intent(EntranceActivity.this, InNodataActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    //如果不是第一次启动，跳转到主界面
                    //测试，跳转到引导界面
                    Intent intent = new Intent(EntranceActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        },2000);

    }
}
