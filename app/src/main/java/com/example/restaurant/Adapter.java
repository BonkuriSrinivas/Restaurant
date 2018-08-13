package com.example.restaurant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    ArrayList<Object> items;
    Context c;
    LayoutInflater inflater;
    static final int ROW = 0;
    static final int HEADER = 1;
    String header;
    IResult lv;
    public static int start, main_c, desert, drink;

    public Adapter(Context c, ArrayList<Object> items, IResult lv) {
        this.c = c;
        this.items = items;
        this.lv = lv;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int pos) {
        return items.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof Item) {
            return ROW;
        }
        return HEADER;
    }

    @Override
    public int getViewTypeCount() {

        return getCount();
    }


    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        int type = getItemViewType(pos);
        if (convertView == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (type) {
                case ROW:
                    convertView = inflater.inflate(R.layout.item_name, null);
                    break;
                case HEADER:
                    convertView = inflater.inflate(R.layout.item_type, null);
                default:
                    break;
            }
        }

        switch (type) {
            case ROW:
                final Item p = (Item) getItem(pos);
                TextView nameTv = (TextView) convertView.findViewById(R.id.nameTv);
                TextView priceTv = (TextView) convertView.findViewById(R.id.priceTv);
                final Button bt_ad = (Button) convertView.findViewById(R.id.bt_add);
                final LinearLayout btn_inc = (LinearLayout) convertView.findViewById(R.id.btn_inc);

                TextView qt_inc = (TextView) convertView.findViewById(R.id.qt_plus);
                TextView qt_dec = (TextView) convertView.findViewById(R.id.qt_minus);
                final TextView qt_q = (TextView) convertView.findViewById(R.id.qt_q);

                nameTv.setText(p.getName().toString());
                priceTv.setText(p.getCost().toString());

                bt_ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bt_ad.setVisibility(View.GONE);
                        btn_inc.setVisibility(View.VISIBLE);

                        if (p.getName().equalsIgnoreCase("Chicken 65")) {
                            start = Integer.valueOf(qt_q.getText().toString());
                        } else if (p.getName().equalsIgnoreCase("Chicken Biryani")) {
                            main_c = Integer.valueOf(qt_q.getText().toString());
                        } else if (p.getName().equalsIgnoreCase("Ice Creams")) {
                            desert = Integer.valueOf(qt_q.getText().toString());
                        } else if (p.getName().equalsIgnoreCase("Pepsi")) {
                            drink = Integer.valueOf(qt_q.getText().toString());
                        }
                        // Toast.makeText(c, "" + , Toast.LENGTH_SHORT).show();
                        // Toast.makeText(c, "" + p.getCost() + " " + p.getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                qt_inc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.valueOf(qt_q.getText().toString()) == 20) {
                            Toast.makeText(c, "Sorry cannot add more than 20 orders per item", Toast.LENGTH_SHORT).show();
                        } else {
                            int qty = Integer.valueOf(qt_q.getText().toString()) + 1;
                            if (qty < 10) {
                                qt_q.setText("0" + String.valueOf(qty));
                            } else {
                                qt_q.setText(String.valueOf(qty));
                            }

                            if (p.getName().equalsIgnoreCase("Chicken 65")) {
                                start = Integer.valueOf(qt_q.getText().toString());
                            } else if (p.getName().equalsIgnoreCase("Chicken Biryani")) {
                                main_c = Integer.valueOf(qt_q.getText().toString());
                            } else if (p.getName().equalsIgnoreCase("Ice Creams")) {
                                desert = Integer.valueOf(qt_q.getText().toString());
                            } else if (p.getName().equalsIgnoreCase("Pepsi")) {
                                drink = Integer.valueOf(qt_q.getText().toString());
                            }

                            if (lv != null) {
                                lv.cart_data(start, main_c, desert, drink);
                            }
                        }
                    }
                });
                qt_dec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.valueOf(qt_q.getText().toString()) == 01) {
                            btn_inc.setVisibility(View.GONE);
                            bt_ad.setVisibility(View.VISIBLE);

                            if (p.getName().equalsIgnoreCase("Chicken 65")) {
                                start = 0;
                            } else if (p.getName().equalsIgnoreCase("Chicken Biryani")) {
                                main_c = 0;
                            } else if (p.getName().equalsIgnoreCase("Ice Creams")) {
                                desert = 0;
                            } else if (p.getName().equalsIgnoreCase("Pepsi")) {
                                drink = 0;
                            }
                            if (lv != null) {
                                lv.cart_data(start, main_c, desert, drink);
                            }
                        } else {
                            int qty = Integer.valueOf(qt_q.getText().toString()) - 1;
                            if (qty < 10) {
                                qt_q.setText("0" + String.valueOf(qty));
                            } else {
                                qt_q.setText(String.valueOf(qty));
                            }

                            if (p.getName().equalsIgnoreCase("Chicken 65")) {
                                start = Integer.valueOf(qt_q.getText().toString());
                            } else if (p.getName().equalsIgnoreCase("Chicken Biryani")) {
                                main_c = Integer.valueOf(qt_q.getText().toString());
                            } else if (p.getName().equalsIgnoreCase("Ice Creams")) {
                                desert = Integer.valueOf(qt_q.getText().toString());
                            } else if (p.getName().equalsIgnoreCase("Pepsi")) {
                                drink = Integer.valueOf(qt_q.getText().toString());
                            }
                            if (lv != null) {
                                lv.cart_data(start, main_c, desert, drink);
                            }

                        }

                    }
                });
                break;
            case HEADER:
                header = (String) getItem(pos);
                TextView headerTv = (TextView) convertView.findViewById(R.id.headerTv);
                headerTv.setText(header);
            default:
                break;
        }

        return convertView;
    }


}
