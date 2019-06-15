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
        final String name[] = getResources().getStringArray(R.array.name);
        String band[] = getResources().getStringArray(R.array.band);
        String attr[] = getResources().getStringArray(R.array.attr);
        String skill[] = getResources().getStringArray(R.array.skill);
        String score[] = getResources().getStringArray(R.array.score);
        TypedArray pic = getResources().obtainTypedArray(R.array.pic);



        db = new MySQLiteOpenHelper(this).getReadableDatabase();


        String sql="select " +
                MySQLiteOpenHelper.CONTACT_ID + ","+
                MySQLiteOpenHelper.CONTACT_NAME + "," +
                MySQLiteOpenHelper.CONTACT_BAND +","+
                MySQLiteOpenHelper.CONTACT_ATTR + ","+
                MySQLiteOpenHelper.CONTACT_SKILL + ","+
                MySQLiteOpenHelper.CONTACT_SCORE + ","+
                MySQLiteOpenHelper.CONTACT_PIC + ","+
                MySQLiteOpenHelper.CONTACT_HOLDER +
                " from " +
                MySQLiteOpenHelper.CONTACT_TABLE;

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.getCount() != 0) {
            cursor.moveToLast();
            for(int i=0; i<cursor.getCount(); i++) {

                Boolean temp;
                if(cursor.getString(7).equals("true"))
                    temp=true;
                else
                    temp=false;
                Card card= new Card(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getInt(6),
                       temp);

                cards.add(card);
                cursor.moveToPrevious();
            }
        }
        cursor.close();
        db.close();


        Log.i("QQQQQ", "onCreate: " + cards.size());
       for(int i = 0 ; i < cards.size();i++)
        Log.i("QQQQQ", "onCreate: "+cards.get(i).name);





        final MyGrid adapter = new MyGrid(this,cards);
        GridView gv = findViewById(R.id.GV);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String n = (String) adapter.getItem(position);
                Log.i("CCCCC", "onItemClick: " + n);

                adapter.getItemId(position);
                String temp;
                cards.get(position).holder = !cards.get(position).holder;
                if(cards.get(position).holder)
                    temp = "true";
                else
                    temp="false";

                db = new MySQLiteOpenHelper(MainActivity.this).getReadableDatabase();

                String sqlupdate="update " +
                        MySQLiteOpenHelper.CONTACT_TABLE +
                        " set " +
                        MySQLiteOpenHelper.CONTACT_HOLDER +
                        " = '" + temp + "'"+
                        " where " + MySQLiteOpenHelper.CONTACT_ID +
                        "=" + id;
                Log.i("TTTT", "onItemClick: " + sqlupdate);

                String sql="select " +
                        MySQLiteOpenHelper.CONTACT_HOLDER +
                        " from " +
                        MySQLiteOpenHelper.CONTACT_TABLE;

                Cursor cursor2 = db.rawQuery(sql, null);

                if(cursor2.getCount() != 0) {
                    cursor2.moveToLast();
                    for(int i=0; i<cursor2.getCount(); i++) {

                        //Log.i("TTTTT2", "onItemClick: "+cursor.getString(1));
                        cursor2.moveToPrevious();
                    }
                }
                cursor2.close();
                db.execSQL(sqlupdate);
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

