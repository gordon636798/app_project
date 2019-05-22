package com.example.bangdream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyGrid adapter = new MyGrid(this);
        GridView gv = findViewById(R.id.GV);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int n = (int)adapter.getItem(position);
                Log.i("CCCCC", "onItemClick: " + n);
            }
        });


/*
        ListView lv = findViewById(R.id.LV);

        lv.setEmptyView(findViewById(R.id.empty));

        Myadapter adapter = new Myadapter(this);

        lv.setAdapter(adapter);

*/
    }
}
