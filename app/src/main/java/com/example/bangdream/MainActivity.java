package com.example.bangdream;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ArrayList<Card> cards = new ArrayList<Card>();

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*final String name[] = getResources().getStringArray(R.array.name);
        String band[] = getResources().getStringArray(R.array.band);
        String attr[] = getResources().getStringArray(R.array.attr);
        String skill[] = getResources().getStringArray(R.array.skill);
        String score[] = getResources().getStringArray(R.array.score);
        TypedArray pic = getResources().obtainTypedArray(R.array.pic);*/





        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        select_fragment sf = new select_fragment();
        ft.replace(R.id.fm1,sf);
        ft.commit();
/*
        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        GridShow gs = new GridShow();
        ft2.replace(R.id.fm2,gs);

        ft2.commit();*/


    }
}

