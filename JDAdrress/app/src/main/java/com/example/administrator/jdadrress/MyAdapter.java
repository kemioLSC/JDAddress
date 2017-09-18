package com.example.administrator.jdadrress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/12.
 */

class MyAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    List<ListBean> data=new ArrayList<>();
    TextView tv1,tv2,tv3,tv4;
    public MyAdapter(Context context, List<ListBean> data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=LayoutInflater.from(context).inflate(R.layout.list_item,null);
        tv1=view.findViewById(R.id.lianxiren);
        tv2=view.findViewById(R.id.dianhua);
        tv3=view.findViewById(R.id.dizhi);
        tv4=view.findViewById(R.id.dizhi_detail);
        tv1.setText(data.get(i).getName());
        tv2.setText(data.get(i).getPhone());
        tv3.setText(data.get(i).getAddr());
        tv4.setText(data.get(i).getAddr_detail());
        return view;
    }
}
