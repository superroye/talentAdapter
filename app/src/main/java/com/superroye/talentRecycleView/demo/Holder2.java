package com.superroye.talentRecycleView.demo;

import android.view.View;
import android.widget.TextView;

import com.superroye.talentrecyclerview.AutoView;
import com.superroye.talentrecyclerview.HolderRes;
import com.superroye.talentrecyclerview.TalentHolder;

/**
 * @author Roye
 * @date 2019/2/11
 */
@HolderRes(R.layout.item_1)
public class Holder2 extends TalentHolder<MultiItem2> {
    public Holder2(View itemView) {
        super(itemView);
    }

    @AutoView
    TextView textView;

    @Override
    public void toView() {
        textView.setText("Holder2 "+itemValue.text);
    }
}
