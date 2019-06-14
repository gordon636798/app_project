package com.example.bangdream;

import android.content.Intent;
import android.content.res.TypedArray;
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

public class MainActivity extends AppCompatActivity {

    ArrayList<Card> cards = new ArrayList<Card>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String name[] = getResources().getStringArray(R.array.name);
        String band[] = getResources().getStringArray(R.array.band);
        String attr[] = getResources().getStringArray(R.array.attr);
        String skill[] = getResources().getStringArray(R.array.skill);
        String score[] = getResources().getStringArray(R.array.score);
        TypedArray pic = getResources().obtainTypedArray(R.array.pic);

        for(int i = 0 ; i < name.length ; i++)
        {
            //Log.i("gg", "onCreate: "+pic.getIndex(i));
            Card card= new Card(name[i],band[i],attr[i],skill[i],score[i],pic.getResourceId(i,0));
            cards.add(card);
        }

        final MyGrid adapter = new MyGrid(this,cards);
        GridView gv = findViewById(R.id.GV);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String n = (String) adapter.getItem(position);
                Log.i("CCCCC", "onItemClick: " + n);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        select_fragment sf = new select_fragment();
        ft.replace(R.id.fm1,sf);

        ft.commit();





/*
        ListView lv = findViewById(R.id.LV);

        lv.setEmptyView(findViewById(R.id.empty));

        Myadapter adapter = new Myadapter(this);

        lv.setAdapter(adapter);

*/
    }
}

