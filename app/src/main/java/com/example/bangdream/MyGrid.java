package com.example.bangdream;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.ArrayList;

public class MyGrid extends BaseAdapter {

    private Context c;
    ArrayList<Card> cards;
    ImageView iv;

    public MyGrid(Context c , ArrayList<Card> cards)
    {
        this.c = c;
        this.cards = cards;

    }
    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {

        return cards.get(position).name;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater lif = LayoutInflater.from(c);
        convertView =  lif.inflate(R.layout.mygrid,null);

        iv = convertView.findViewById(R.id.imageView);
        Log.i("ggggg", "getView: "+cards.get(position).pic);
        iv.setImageResource(cards.get(position).pic);
       // ImageView iv2 = convertView.findViewById(R.id.imageView2);
        //iv2.setImageResource(R.drawable.check);
        return convertView;
    }


}
