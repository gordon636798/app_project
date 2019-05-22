package com.example.bangdream;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MyGrid extends BaseAdapter {

    private Context c;
    private TypedArray pic ;
    Card cards;
    ImageView iv;
    public MyGrid(Context c)
    {
        this.c = c;
        pic = c.getResources().obtainTypedArray(R.array.pic);
        String[] skill = c.getResources().getStringArray(R.array.skill);
        String[] band = c.getResources().getStringArray(R.array.band);
        String[] attr = c.getResources().getStringArray(R.array.attr);

        cards = new Card(skill,band,attr,pic);

    }
    @Override
    public int getCount() {
        return pic.length();
    }

    @Override
    public Object getItem(int position) {

        return cards.num[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lif = LayoutInflater.from(c);

        convertView =  lif.inflate(R.layout.mygrid,null);

        iv = convertView.findViewById(R.id.imageView);
        iv.setImageResource(pic.getResourceId(position,0));
        return convertView;
    }


}
