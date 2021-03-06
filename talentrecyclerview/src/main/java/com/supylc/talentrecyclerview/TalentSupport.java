package com.supylc.talentrecyclerview;

import android.support.v7.widget.RecyclerView;

import com.supylc.talentrecyclerview.support.LoadMoreBean;
import com.supylc.talentrecyclerview.support.LoadMoreHolder;
import com.supylc.talentrecyclerview.support.LoadMoreSupport;
import com.supylc.talentrecyclerview.support.NullHolder;
import com.supylc.talentrecyclerview.support.NullObject;
import com.supylc.talentrecyclerview.support.Publisher;

/**
 * @author Roye
 * @date 2019/4/17
 */
public class TalentSupport {

    private NullObject nullObject;
    private LoadMoreSupport loadMoreSupport;
    private Publisher publisher;

    public TalentSupport(TalentAdapter talentAdapter) {
        this.nullObject = new NullObject();
        talentAdapter.registerHolder(NullHolder.class);
        talentAdapter.registerHolder(LoadMoreHolder.class);

        loadMoreSupport = new LoadMoreSupport(talentAdapter);
        publisher = new Publisher();
        talentAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                loadMoreSupport.onItemsChange();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                loadMoreSupport.onItemsChange();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                loadMoreSupport.onItemsChange();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                loadMoreSupport.onItemsChange();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                loadMoreSupport.onItemsChange();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                loadMoreSupport.onItemsChange();
            }
        });
    }

    public LoadMoreSupport getLoadMoreSupport() {
        return loadMoreSupport;
    }

    public NullObject getNullObject() {
        return nullObject;
    }

    public LoadMoreBean getTalentLoadmore() {
        return loadMoreSupport.getTalentLoadmore();
    }

    public Publisher getPublisher() {
        return publisher;
    }
}
