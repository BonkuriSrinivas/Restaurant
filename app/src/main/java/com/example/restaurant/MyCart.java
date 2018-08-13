package com.example.restaurant;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MyCart extends AppCompatActivity {


    Bundle bundle;
    int cost = 0;
    static TextView tv_tt_cost;
    ListView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("");

        toolbar.setNavigationIcon(R.mipmap.leftarrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_tt_cost = (TextView) findViewById(R.id.tt_costtt);
        lv = (ListView) findViewById(R.id.cart_list);
        bundle = getIntent().getExtras();
        if (bundle != null) {

            cost = bundle.getInt("total");
            tv_tt_cost.setText("RS " + String.valueOf(cost));
        }

        if(MainActivity.cart_data.size()>2)
        {

        }
        Adapter1 adapter1 = new Adapter1(this, MainActivity.cart_data);
        lv.setAdapter(adapter1);
    }
}
