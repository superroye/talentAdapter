package com.supylc.talentrecyclerview;

import android.view.View;

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
