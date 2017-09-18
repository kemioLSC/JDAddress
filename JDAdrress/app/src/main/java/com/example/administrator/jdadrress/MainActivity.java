package com.example.administrator.jdadrress;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ListBean> list=new ArrayList<>();
    ListView listView;
    MyAdapter adapter;
    String name,phone,address,address_detail;
    private Handler handler;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //去掉ActionBar
            getSupportActionBar().hide();
            Log.e("hhhhhhhh", "oncreate"+ list.toString());
            listView= (ListView) findViewById(R.id.mylist);
            adapter=new MyAdapter(MainActivity.this,list);
            Intent data=getIntent();
            if(data.getStringExtra("from").equals("fromfirstAdd")){
                ListBean listBean = new ListBean();
                name = data.getStringExtra("name");
                phone = data.getStringExtra("phone");
                address = data.getStringExtra("address");
                address_detail=data.getStringExtra("address_detail");
                listBean.setName(name);
                listBean.setPhone(phone);
                listBean.setAddr(address);
                listBean.setAddr_detail(address_detail);
                list.add(listBean);
            }

        Log.e("hhhhhhhh", "oncreate"+ list.toString());

            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if(msg.what == 1){
                        adapter.notifyDataSetChanged();
                    }
                }
            };
            listView.setAdapter(adapter);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    list.remove(i);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    if(list.size()==0){
                        Intent intent1=new Intent(MainActivity.this,InNodataActivity.class);
                        startActivity(intent1);
                    }
                    return true;
                }
            });
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent2=new Intent(MainActivity.this,AddActivity.class);
                    intent2.putExtra("name",list.get(i).getName());
                    intent2.putExtra("phone",list.get(i).getPhone());
                    intent2.putExtra("addr",list.get(i).getAddr());
                    intent2.putExtra("addr_detail",list.get(i).getAddr_detail());
                    intent2.putExtra("from","fromItem");
                    index=i;
                    Log.e("lsc", "onItemClick: "+list.get(i));
                    startActivityForResult(intent2,2);
                }
            });



    }
    public void add(View view){
        Intent intent=new Intent(MainActivity.this,AddActivity.class);
        intent.putExtra("from","fromAdd");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            ListBean listBean = new ListBean();
            name = data.getStringExtra("name");
            phone = data.getStringExtra("phone");
            address = data.getStringExtra("address");
            address_detail = data.getStringExtra("address_detail");
            listBean.setName(name);
            listBean.setPhone(phone);
            listBean.setAddr(address);
            listBean.setAddr_detail(address_detail);
            if (requestCode == 1) {
                if (!(data.getStringExtra("from").equals("fromBack"))) {
                    list.add(listBean);
                    Log.e("MainActivity", "收到消息：" + list.toString());
                }
            } else if (requestCode == 2) {
                if (!(data.getStringExtra("from").equals("fromBack"))) {
                    list.remove(index);
                    list.add(index, listBean);
                }
                Log.e("ooooo", "onActivityResult: " + list.toString());
            } else {
                Log.e("hhhhhhhh", "onActivityResult: " + "手机屏幕点击返回" + list.toString());
            }
            Log.e("hhhhhhhh", "onActivityResult: " + "onActivityResult" + list.toString());
        }
            handler.sendEmptyMessage(1);
    }


}
