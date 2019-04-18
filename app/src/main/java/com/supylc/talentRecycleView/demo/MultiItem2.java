package com.supylc.talentRecycleView.demo;

import com.supylc.talentrecyclerview.TalentItemType;

import java.io.Serializable;

public class MultiItem2 implements Serializable, TalentItemType {

    public static final int TYPE_1 = 1;
    public static final int TYPE_2 = 2;
    public static final int TYPE_3 = 3;

    public String text;
    private int type;

    public MultiItem2(int type, String text) {
        this.text = text;
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }
}