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

    public Card(String name,String band,String attr,String skill,String score,int pic)
    {
        this.name = name;
        this.band = band;
        this.attr = attr;
        this.skill = skill;
        this.score = score;
        this.pic = pic;
    }

}
