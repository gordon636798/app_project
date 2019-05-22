package com.example.bangdream;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Myadapter extends BaseAdapter{

    TypedArray pic ;
    private LayoutInflater lif;
    public Myadapter(Context c)
    {
        lif = LayoutInflater.from(c);
        pic = c.getResources().obtainTypedArray(R.array.pic);
        Log.i("VV1", "getView: ");
    }

    @Override
    public int getCount() {
        return pic.length()/5+1;
    }

    @Override
    public Object getItem(int position) {
        return pic.getIndex(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("VV2", "getView: ");
        convertView = lif.inflate(R.layout.mylist,null);
        Log.i("VV3", "getView: ");
        ImageButton iv = convertView.findViewById(R.id.imageButton);
        ImageButton iv1 = convertView.findViewById(R.id.imageButton1);
        ImageButton iv2 = convertView.findViewById(R.id.imageButton2);
        ImageButton iv3 = convertView.findViewById(R.id.imageButton3);
        ImageButton iv4 = convertView.findViewById(R.id.imageButton4);

            iv.setImageResource(pic.getResourceId(position*5,0));
            iv1.setImageResource(pic.getResourceId(position*5+1,0));
            iv2.setImageResource(pic.getResourceId(position*5+2,0));
            iv3.setImageResource(pic.getResourceId(position*5+3,0));
            iv4.setImageResource(pic.getResourceId(position*5+4,0));



        return convertView;
    }
}
