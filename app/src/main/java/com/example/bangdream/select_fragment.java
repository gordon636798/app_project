package com.example.bangdream;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class select_fragment extends Fragment {
    //private CheckBox CheckBoxA1,CheckBoxA2,CheckBoxA3,CheckBoxA4;
   // private CheckBox CheckBoxB1,CheckBoxB2,CheckBoxB3,CheckBoxB4,CheckBoxB5;
    String[] a1 = {"red","blue","green","orange"};
    String[] b1 = {"PPP","af","PP","R","HHW"};
    CheckBox[] A1 = new CheckBox[4];
    CheckBox[] B1 = new CheckBox[5];
    Boolean[] A1_CB={false,false,false,false};
    Boolean[] B1_CB={false,false,false,false,false};
    Bundle bundle=new Bundle();

    ArrayList<String> attr = new ArrayList<String>();
    ArrayList<String> band = new ArrayList<String>();

    public select_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_select_fragment, container, false);

        A1[0] = (CheckBox) view.findViewById(R.id.red);
        A1[1] = (CheckBox) view.findViewById(R.id.blue);
        A1[2] = (CheckBox) view.findViewById(R.id.green);
        A1[3] = (CheckBox) view.findViewById(R.id.orange);
        B1[0]=(CheckBox) view.findViewById(R.id.PPP);
        B1[1]=(CheckBox) view.findViewById(R.id.af);
        B1[2]=(CheckBox) view.findViewById(R.id.PP);
        B1[3]=(CheckBox) view.findViewById(R.id.R);
        B1[4]=(CheckBox) view.findViewById(R.id.HHW);
       for(int i=0;i<4;i++)
            A1[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        for(int i=0;i<5;i++)
            B1[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
       for(int i=0;i<4;i++) {
           if (A1[i].isChecked()) {
                A1_CB[i]=true;
                //bundle.putInt(a1[i],1);
           }
           else {
               A1_CB[i] = false;
               //bundle.putInt(a1[i],0);
           }
       }
        for(int i=0;i<5;i++) {
            if (B1[i].isChecked()) {
                B1_CB[i]=true;
                //bundle.putInt(b1[i],1);
            }
            else {
                B1_CB[i] = false;
                //bundle.putInt(b1[i],0);
            }
        }
        CHECK();

        Button btOK =view.findViewById(R.id.OK);

        btOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHECK();

            }
        });

        return view;


    }

    public void CHECK()
    {
        attr = new ArrayList<String>();
        band = new ArrayList<String>();

        if(A1[0].isChecked())
        {
            Log.i("SSSS", "onClick: "+A1[0].isChecked());
            attr.add("Powerful");
        }
        if(A1[1].isChecked())
        {
            attr.add("Cool");
        }
        if(A1[2].isChecked())
        {
            attr.add("Pure");
        }
        if(A1[3].isChecked())
        {
            attr.add("Happy");
        }
        if(B1[0].isChecked())
        {
            attr.add("Happy");
        }
        if(B1[1].isChecked())
        {
            band.add("ppp");
        }
        if(B1[2].isChecked())
        {
            band.add("af");
        }
        if(B1[3].isChecked())
        {
            band.add("p*p");
        }
        if(B1[4].isChecked())
        {
            band.add("hhw");
        }

        GridShow gs = new GridShow();
        Bundle bundle2 = new Bundle();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        bundle2.putStringArrayList("attr",attr);
        bundle2.putStringArrayList("band",band);
        ft.replace(R.id.fm2,gs);
        gs.setArguments(bundle2);
        ft.commit();
    }

}
