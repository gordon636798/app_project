package com.example.bangdream;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    ImageView iv2;
    int mCurrentPos;

    public MyGrid(Context c , ArrayList<Card> cards)
    {
        this.c = c;
        this.cards = cards;
        Log.i("SSSSSSSSSSS", "MyGrid: "+cards.size());

    }
    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {

        return cards.get(position).primary;
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
        //Log.i("ggggg", "getView: "+cards.get(position).name);
        iv.setImageResource(cards.get(position).pic);
        iv2 = convertView.findViewById(R.id.imageView2);
        if(cards.get(position).holder)
            iv2.setVisibility(View.VISIBLE);
        else
            iv2.setVisibility(View.INVISIBLE);
       // ImageView iv2 = convertView.findViewById(R.id.imageView2);
        //iv2.setImageResource(R.drawable.check);
        return convertView;
    }


}
