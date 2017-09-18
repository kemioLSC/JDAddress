package com.example.administrator.jdadrress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InNodataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_in_nodata);
        //去掉ActionBar
        getSupportActionBar().hide();
    }
    public void add(View view){
        Intent intent=new Intent(InNodataActivity.this,AddActivity.class);
        intent.putExtra("from","fromfirstAdd");
        startActivity(intent);
    }
}
