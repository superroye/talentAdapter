package com.supylc.talentrecyclerview.support;

import android.view.View;
import android.widget.TextView;

import com.supylc.talentrecyclerview.HolderRes;
import com.supylc.talentrecyclerview.R;
import com.supylc.talentrecyclerview.TalentHolder;

/**
 * @author Roye
 * @date 2019/4/17
 */
@HolderRes(resName = "layout_recycleview_loadmore")
public class LoadMoreHolder extends TalentHolder<LoadMoreBean> {

    public LoadMoreHolder(View itemView) {
        super(itemView);
    }

    TextView textView;

    @Override
    public void initView() {
        textView = findV(R.id.text);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post("");
            }
        });
    }

    @Override
    public void toView() {
        if (LoadMoreBean.STATUS_READY == itemValue.getStatus()) {
            textView.setText("加载更多");
        } else if (LoadMoreBean.STATUS_LOADING == itemValue.getStatus()) {
            textView.setText("加载中...");
        } else {
            textView.setText("没有更多了~");
        }
    }
}
