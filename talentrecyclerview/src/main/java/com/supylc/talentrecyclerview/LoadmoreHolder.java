package com.supylc.talentrecyclerview;

import android.view.View;
import android.widget.TextView;

/**
 * @author Roye
 * @date 2019/4/17
 */
@HolderRes(resName = "layout_recycleview_loadmore")
public class LoadmoreHolder extends TalentHolder<TalentLoadmore> {

    public LoadmoreHolder(View itemView) {
        super(itemView);
    }

    TextView textView;

    @Override
    public void initView() {
        textView = findV(R.id.text);
    }

    @Override
    public void toView() {
        if (itemValue.isHasMore()) {
            textView.setText("加载中...");
        } else {
            textView.setText(itemValue.getText());
        }
    }
}
