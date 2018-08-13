package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter1 extends BaseAdapter {
    Context context;
    ArrayList<Cart_Item> cart_data = new ArrayList<Cart_Item>();
    LayoutInflater inflter;

    public Adapter1(Context applicationContext, ArrayList<Cart_Item> cart_data) {
        this.context = applicationContext;
        this.cart_data = cart_data;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cart_data.size();
    }

    @Override
    public Object getItem(int i) {
        return cart_data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        convertView = inflter.inflate(R.layout.activity_list, null);

        TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv);
        TextView priceTv = (TextView) convertView.findViewById(R.id.priceTv);
        TextView qt_q = (TextView) convertView.findViewById(R.id.qt_q);

        Cart_Item p = (Cart_Item) getItem(i);
        nameTv.setText(p.getName());
        priceTv.setText(p.getCost());

        if (Integer.valueOf(p.getCount()) < 10) {
            qt_q.setText("0" + p.getCount());
        } else {
            qt_q.setText(p.getCount());
        }
        return convertView;
    }
}
