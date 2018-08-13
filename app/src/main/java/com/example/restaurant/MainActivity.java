package com.example.restaurant;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IResult {

    Button bt_view_cart;
    RelativeLayout relativeLayout, menu_lay;


    String[] items = {"Chicken 65", "Chicken Biryani", "Ice Creams", "Pepsi"};

    String[] prices = {"Rs 400", "Rs 500", "Rs 100", "Rs 100"};

    public static ArrayList<Cart_Item> cart_data = new ArrayList<Cart_Item>();
    ListView lv;
    Toolbar toolbar;

    ImageView im_upload, im_info;
    int total_cost = 0;
    int start, main_c, deser, drinks;
    TextView tv_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("");

        toolbar.setNavigationIcon(R.mipmap.leftarrow);

        relativeLayout = (RelativeLayout) findViewById(R.id.topPanel);
        menu_lay = (RelativeLayout) findViewById(R.id.menu_lay);
        bt_view_cart = (Button) findViewById(R.id.view_cart);

        im_upload = (ImageView) findViewById(R.id.upload);
        im_info = (ImageView) findViewById(R.id.info);
        tv_call = (TextView) findViewById(R.id.call);

        im_upload.setOnClickListener(this);
        im_info.setOnClickListener(this);
        menu_lay.setOnClickListener(this);

        lv = (ListView) findViewById(R.id.menu_list);

        tv_call.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent callIntent = new Intent(Intent.ACTION_DIAL);
                                           startActivity(callIntent.setData(Uri.parse("tel:"+"9854562142")));
                                       }
                                   }
        );

        Adapter adapter = new Adapter(this, getMenu(), (IResult) this);
        lv.setAdapter(adapter);

    }

    private ArrayList<Object> getMenu() {
        Item p0 = new Item(items[0], prices[0]);
        Item p1 = new Item(items[1], prices[1]);
        Item p2 = new Item(items[2], prices[2]);
        Item p3 = new Item(items[3], prices[3]);

        ArrayList<Object> menu = new ArrayList<Object>();

        menu.add("Starters");
        menu.add(p0);

        menu.add("Main Course");
        menu.add(p1);

        menu.add("Desserts");
        menu.add(p2);

        menu.add("Drinks");
        menu.add(p3);

        return menu;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_lay:
                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.menu_book, null);
                final PopupWindow popupWindow = new PopupWindow(customView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(relativeLayout, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                TextView textView1 = (TextView) customView.findViewById(R.id.tv_st);
                TextView textView2 = (TextView) customView.findViewById(R.id.tv_mc);
                TextView textView3 = (TextView) customView.findViewById(R.id.tv_de);
                TextView textView4 = (TextView) customView.findViewById(R.id.tv_dri);

                TextView textView5 = (TextView) customView.findViewById(R.id.tv_st1);
                TextView textView6 = (TextView) customView.findViewById(R.id.tv_mc1);
                TextView textView7 = (TextView) customView.findViewById(R.id.tv_de1);
                TextView textView8 = (TextView) customView.findViewById(R.id.tv_dri1);

                textView5.setText(String.valueOf(Adapter.start));
                textView6.setText(String.valueOf(Adapter.main_c));
                textView7.setText(String.valueOf(Adapter.desert));
                textView8.setText(String.valueOf(Adapter.drink));

                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv.smoothScrollToPosition(0);
                        popupWindow.dismiss();

                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv.smoothScrollToPosition(2);
                        popupWindow.dismiss();
                    }
                });
                textView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv.smoothScrollToPosition(4);
                        popupWindow.dismiss();
                    }
                });
                textView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lv.smoothScrollToPosition(7);
                        popupWindow.dismiss();
                    }
                });

                break;
            case R.id.upload:
                Intent intent2 = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent2);
                break;

            case R.id.info:
                Intent intent1 = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private ArrayList<Cart_Item> getView_cart(int s1, int s2, int s3, int s4) {

        Cart_Item cart_item = new Cart_Item();
        if (s1 != 0) {
            cart_item.setName("Chicken 65");
            cart_item.setCount(String.valueOf(s1));
            cart_item.setCost("400");
        }
        if (s2 != 0) {
            cart_item.setName("Main Course");
            cart_item.setCount(String.valueOf(s2));
            cart_item.setCost("500");
        }

        if (s3 != 0) {
            cart_item.setName("Desserts");
            cart_item.setCount(String.valueOf(s3));
            cart_item.setCost("100");
        }

        if (s4 != 0) {
            cart_item.setName("Drinks");
            cart_item.setCount(String.valueOf(s4));
            cart_item.setCost("100");
        }

        cart_data.add(cart_item);

        return cart_data;

    }

    @Override
    public void cart_data(final int s1, final int s2, final int s3, final int s4) {
        start = s1;
        main_c = s2;
        deser = s3;
        drinks = s4;
        bt_view_cart.setText("VIEW CART" + "(" + String.valueOf(s1 + s2 + s3 + s4) + ") ITEMS");
        total_cost = ((s1 * 400) + (s2 * 500) + (s3 * 100) + (s4 * 100));


        bt_view_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cart_data = getView_cart(s1, s2, s3, s4);


                Intent intent = new Intent(MainActivity.this, MyCart.class).putExtra("total", total_cost);
                startActivity(intent);
            }
        });
    }
}
