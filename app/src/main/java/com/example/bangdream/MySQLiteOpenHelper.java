package com.example.bangdream;


import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MySQLiteOpenHelper extends SQLiteOpenHelper{
    final static String DB_Name = "ContactsDB";
    final static int DB_Version = 1;
    final static String CONTACT_TABLE = "contacts";
    final static String CONTACT_ID = "id";
    final static String CONTACT_NAME = "name";
    final static String CONTACT_BAND = "band";
    final static String CONTACT_ATTR = "attr";
    final static String CONTACT_SKILL = "skill";
    final static String CONTACT_SCORE = "score";
    final static String CONTACT_PIC = "pic";
    final static String CONTACT_HOLDER = "holder";
    Context context;



    public MySQLiteOpenHelper(Context context){
        super(context, DB_Name, null, DB_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "create table " + CONTACT_TABLE + "("
                + CONTACT_ID + " integer primary key autoincrement, "
                + CONTACT_NAME + " text, "
                + CONTACT_BAND + " text, "
                + CONTACT_ATTR + " text, "
                + CONTACT_SKILL + " test,"
                + CONTACT_SCORE + " test,"
                + CONTACT_PIC + " integer,"
                + CONTACT_HOLDER + " text)";
        Log.i("AAAAAA", "onCreate: " + sql);
        db.execSQL(sql);


        String name[] = context.getResources().getStringArray(R.array.name);
        String band[] = context.getResources().getStringArray(R.array.band);
        String attr[] = context.getResources().getStringArray(R.array.attr);
        String skill[] = context.getResources().getStringArray(R.array.skill);
        String score[] = context.getResources().getStringArray(R.array.score);
        TypedArray pic = context.getResources().obtainTypedArray(R.array.pic);

        for(int i = 0 ; i < name.length ; i++) {
            //Log.i("gg", "onCreate: "+pic.getIndex(i));


            ContentValues values = new ContentValues();
            values.put(MySQLiteOpenHelper.CONTACT_NAME, name[i]);
            values.put(MySQLiteOpenHelper.CONTACT_BAND, band[i]);
            values.put(MySQLiteOpenHelper.CONTACT_ATTR, attr[i]);
            values.put(MySQLiteOpenHelper.CONTACT_SKILL, attr[i]);
            values.put(MySQLiteOpenHelper.CONTACT_SCORE, attr[i]);
            values.put(MySQLiteOpenHelper.CONTACT_PIC, pic.getResourceId(i,0));
            values.put(MySQLiteOpenHelper.CONTACT_HOLDER, "false");

            Log.i("XXXXX", "saveContact: " + name[i] + " " + band[i] + " " + attr[i]);

            db.insert(MySQLiteOpenHelper.CONTACT_TABLE.toString(), null, values);

        }
        pic.recycle();
        String sql2="select " +
                MySQLiteOpenHelper.CONTACT_HOLDER + ","+
                MySQLiteOpenHelper.CONTACT_ID + ","+
                MySQLiteOpenHelper.CONTACT_NAME +
                " from " +
                MySQLiteOpenHelper.CONTACT_TABLE;

        Cursor cursor2 = db.rawQuery(sql2, null);
        Log.i("ZZZZ", "onItemClick: " + cursor2.getCount());
        if(cursor2.getCount() != 0) {
            cursor2.moveToLast();
            for(int i=0; i<cursor2.getCount(); i++) {

                Log.i("ZZZZZZ", "onItemClick: "+cursor2.getString(0) +" "+ cursor2.getString(1) +" "+ cursor2.getString(2));
                cursor2.moveToPrevious();
            }
        }

        cursor2.close();

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists " + CONTACT_TABLE);
        onCreate(db);
    }

}
