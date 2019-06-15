package com.example.bangdream;

import android.content.Context;
import android.content.res.TypedArray;

public class Card {
    public String name;
    public String band;
    public String attr;
    public String skill;
    public String score;
    public int pic;
    public boolean holder;
    public int primary;

    public Card(String name,String band,String attr,String skill,String score,int pic,boolean holder,int primary)
    {
        this.primary=primary;
        this.name = name;
        this.band = band;
        this.attr = attr;
        this.skill = skill;
        this.score = score;
        this.pic = pic;
        this.holder=holder;
    }

}
