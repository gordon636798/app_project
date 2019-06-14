package com.example.bangdream;

import android.content.Context;
import android.content.res.TypedArray;

public class Card {

    //public String[] name;
    public String[] skill;
    public String[] band;
    public String[] attr;
    public TypedArray img;
    public boolean[] state;
    public int[] score;
    public int[] num;

    public Card(String[] skill, String[] band, String[] attr, TypedArray obtainTypedArray) {
        this.skill = skill;
        this.band = band;
        this.attr  = attr;
        this.img = img;
        state = new boolean[skill.length];
        num = new int[skill.length];
        for(int i=0;i<skill.length;i++)
        {
            state[i]=false;
            num[i]=i;
        }
    }

}
