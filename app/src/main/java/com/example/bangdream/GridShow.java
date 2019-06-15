package com.example.bangdream;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridShow extends Fragment {

    SQLiteDatabase db;
    ArrayList<Card> cards = new ArrayList<Card>();
    public GridShow() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_show, container, false);

        db = new MySQLiteOpenHelper(view.getContext()).getReadableDatabase();
        ArrayList<String> attr0 = new ArrayList<String>();
        ArrayList<String> band0 = new ArrayList<String>();
        Bundle bundle = getArguments();
        if(bundle != null)
        {
            attr0 = bundle.getStringArrayList("attr");
            band0 = bundle.getStringArrayList("band");
        }
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
                MySQLiteOpenHelper.CONTACT_TABLE  ;

        final Cursor cursor = db.rawQuery(sql, null);

        Log.i("KKKKKK", "onCreateView: " + attr0.isEmpty() + band0.isEmpty());
        if(cursor.getCount() != 0) {
            cursor.moveToLast();
            for(int i=0; i<cursor.getCount(); i++) {


                if(!attr0.isEmpty() && !band0.isEmpty())
                if(attr0.contains(cursor.getString(3)) && band0.contains(cursor.getString(2)))
                {
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
                            temp,
                            cursor.getInt(0));
                    cards.add(card);
                }
/*
                else if(attr0.isEmpty() && !band0.isEmpty())
                    if(band0.contains(cursor.getString(2)))
                    {
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
                    }
                else if(!attr0.isEmpty() && band0.isEmpty())
                    if(attr0.contains(cursor.getString(3)))
                    {
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
                    }
                else
                    if(true)
                    {
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
                    }*/

                Log.i("88888", "onCreate: " +
                        cursor.getString(0) +" "+
                        cursor.getString(1)+" "+
                        cursor.getString(2)+" "+
                        cursor.getString(3)+" "+
                        cursor.getString(4)+" "+
                        cursor.getString(5)+" "+
                        cursor.getInt(6)+" "+
                        cursor.getString(7)
                );

                cursor.moveToPrevious();



            }
        }
        cursor.close();
        db.close();



        final MyGrid adapter = new MyGrid(view.getContext(),cards);
        GridView gv = view.findViewById(R.id.GV);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int n = (int) adapter.getItem(position);
                Log.i("CCCCC", "onItemClick: " + n);

                adapter.getItemId(position);
                String temp;
                cards.get(position).holder = !cards.get(position).holder;
                if(cards.get(position).holder)
                    temp = "true";
                else
                    temp="false";
                Log.i("TT2", "onItemClick: "+temp);
                db = new MySQLiteOpenHelper(view.getContext()).getReadableDatabase();

                int tempInt = cards.size() - position;
                String sqlupdate="update " +
                        MySQLiteOpenHelper.CONTACT_TABLE +
                        " set " +
                        MySQLiteOpenHelper.CONTACT_HOLDER +
                        " = '" + temp + "'"+
                        " where " + MySQLiteOpenHelper.CONTACT_ID +
                        "=" + adapter.getItem(position);
                Log.i("TTTT", "onItemClick: " + sqlupdate);
                adapter.notifyDataSetChanged();
                db.execSQL(sqlupdate);
                db.close();


            }
        });
        return view;
    }

}
