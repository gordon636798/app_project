package com.example.bangdream;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 */
public class GridShow extends Fragment {

    SQLiteDatabase db;
    ArrayList<Card> cards = new ArrayList<Card>();
    public GridShow() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
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
            ArrayList<Card> tempC = new ArrayList<Card>();
            for(int i=0; i<cursor.getCount(); i++) {

                if(bundle.getBoolean("match"))
                {
                    if(cursor.getString(7).equals("true"))
                    {
                        //Card(String name,String band,String attr,String skill,String score,int pic,boolean holder,int primary)


                        Card card= new Card(cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getString(5),
                                cursor.getInt(6),
                                true,
                                cursor.getInt(0));
                        tempC.add(card);

                    }

                }
                else if(!attr0.isEmpty() && !band0.isEmpty())
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

            if(bundle.getBoolean("match")){
                MATCH(tempC);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void MATCH(ArrayList<Card> tempC)
    {
        tempC.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                if(Integer.parseInt(o1.score)< Integer.parseInt(o2.score))
                    return 1;
                else
                    return -1;
            }
        });


        for(int j=0;j<tempC.size();j++)
        {
            if(tempC.get(j).attr.equals("Cool") && tempC.get(j).band.equals("R"))
            {
                cards.add(tempC.get(j));

            }
        }

        for(int j=0;j<tempC.size();j++)
        {
            if(tempC.get(j).attr.equals("Cool") && !cards.contains(tempC.get(j)))
            {
                cards.add(tempC.get(j));

            }
        }
        for(int j=0;j<tempC.size();j++)
        {
            if(tempC.get(j).band.equals("R") && !cards.contains(tempC.get(j)))
            {
                cards.add(tempC.get(j));

            }
        }
        for(int j=0;j<tempC.size();j++)
        {
            if(!cards.contains(tempC.get(j)))
            {
                cards.add(tempC.get(j));

            }
        }
        for(int j=0;j<tempC.size();j++)
        {
            Log.i("JJJJJJ", "onCreateView: "+tempC.size()+" "+cards.get(j).name+" "+cards.get(j).attr);
        }
    }

}
