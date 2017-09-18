package com.example.administrator.jdadrress;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;


public class AddActivity extends AppCompatActivity {


    EditText edt1,edt2,edt3;
    TextView tv;
    Intent intent1;
    String FromWhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //去掉ActionBar
        getSupportActionBar().hide();
        edt1= (EditText) findViewById(R.id.name);
        edt2= (EditText) findViewById(R.id.phone);
        edt3= (EditText) findViewById(R.id.adress_detail);
        tv= (TextView) findViewById(R.id.address);
        intent1=getIntent();
        FromWhat=intent1.getStringExtra("from");
        change();
    }
    //Texview的点击事件
    public void chooseArea(View view) {
        //判断输入法的隐藏状态
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            selectAddress();//调用CityPicker选取区域

        }
    }
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(AddActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //为TextView赋值
                tv.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });

    }

    private void change() {
        String na,ph,addr,addr_detail;
        Intent intent=getIntent();
        na=intent.getStringExtra("name");
        ph=intent.getStringExtra("phone");
        addr=intent.getStringExtra("addr");
        addr_detail=intent.getStringExtra("addr_detail");
        edt1.setText(na);
        edt2.setText(ph);
        tv.setText(addr);
        edt3.setText(addr_detail);
    }

    public void save(View view){
        String name,phone,adrress,adress_detail;
        name=edt1.getText().toString();
        phone=edt2.getText().toString();
        adrress=tv.getText().toString();
        adress_detail=edt3.getText().toString();
        if(!(name.equals("")||phone.equals("")||adrress.equals("")||adress_detail.equals(""))){
            Intent intent=new Intent(AddActivity.this,MainActivity.class);
            intent.putExtra("name",edt1.getText().toString());
            intent.putExtra("phone",edt2.getText().toString());
            intent.putExtra("address",tv.getText().toString());
            intent.putExtra("address_detail",edt3.getText().toString());
            intent.putExtra("from",FromWhat);
            if(FromWhat.equals("fromfirstAdd")){
                intent.putExtra("from","fromfirstAdd");
                startActivity(intent);
            }else if (FromWhat.equals("fromAdd")){
                setResult(1,intent);
                finish();
            }else if(FromWhat.equals("fromItem")){
                setResult(2,intent);
                finish();
            }
        }else{
            Toast.makeText(AddActivity.this,"请将信息填写完整",Toast.LENGTH_SHORT).show();
        }
        Log.e("MainActivity","addActivity"+edt1.getText().toString());
        Log.e("MainActivity","addActivity"+edt2.getText().toString());
        Log.e("MainActivity","addActivity"+tv.getText().toString());
        Log.e("MainActivity","addActivity"+edt3.getText().toString());
    }
    public void ClickBack(View view){
        if(intent1.getStringExtra("from").equals("fromfirstAdd")){
            Intent intent=new Intent(AddActivity.this,InNodataActivity.class);
            startActivity(intent);
        }else{
            Intent intent=new Intent(AddActivity.this,MainActivity.class);
            intent.putExtra("from","fromBack");
                setResult(1,intent);
                finish();
            }


    }
}
