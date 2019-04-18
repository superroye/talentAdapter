package com.supylc.talentrecyclerview.support;

import android.view.View;

import com.supylc.talentrecyclerview.HolderRes;
import com.supylc.talentrecyclerview.TalentHolder;

/**
 * @author Roye
 * @date 2019/4/17
 */
@HolderRes(resName = "layout_recycleview_null")
public class NullHolder extends TalentHolder<NullObject> {
    public NullHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void toView() {

    }
}
