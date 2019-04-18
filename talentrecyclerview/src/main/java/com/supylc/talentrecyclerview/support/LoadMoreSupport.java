package com.supylc.talentrecyclerview.support;

import android.support.v7.widget.RecyclerView;

import com.supylc.talentrecyclerview.TalentAdapter;

/**
 * @author Roye
 * @date 2019/4/18
 */
public class LoadMoreSupport implements LoadMore {

    private LoadMore loadMoreDelegate;
    private EndScrollerListener endScrollerListener;
    private RecyclerView recyclerView;
    private TalentAdapter talentAdapter;
    private LoadMoreBean talentLoadmore;
    private Subscriber clickObserver;

    public LoadMoreSupport(TalentAdapter talentAdapter) {
        this.talentAdapter = talentAdapter;
        this.talentLoadmore = new LoadMoreBean();
    }

    public void setLoadMore(RecyclerView recyclerView, LoadMore loadMore) {
        this.loadMoreDelegate = loadMore;
        this.recyclerView = recyclerView;

        if (LoadMore.MODE_AUTO == loadMore.loadMode()) {
            if (endScrollerListener != null) {
                recyclerView.removeOnScrollListener(endScrollerListener);
            }
            endScrollerListener = new EndScrollerListener(this);
            recyclerView.addOnScrollListener(endScrollerListener);
        } else if (LoadMore.MODE_CLICK == loadMore.loadMode()) {
            clickObserver = new Subscriber() {
                @Override
                public void update(HolderMessage message) {
                    if (message.getItemValue() instanceof LoadMoreBean) {
                        onLoadMore();
                    }
                }
            };
            talentAdapter.publisher().subscribe(clickObserver);
        }
        refreshLoadmore(LoadMoreBean.STATUS_READY);
    }

    public boolean isNeedLoadmore() {
        return loadMoreDelegate != null;
    }

    @Override
    public int loadMode() {
        return loadMoreDelegate.loadMode();
    }

    @Override
    public void onLoadMore() {
        if (LoadMoreBean.STATUS_READY != talentLoadmore.getStatus()) {
            return;
        }
        refreshLoadmore(LoadMoreBean.STATUS_LOADING);
        loadMoreDelegate.onLoadMore();
    }

    @Override
    public boolean hasNext() {
        return loadMoreDelegate.hasNext();
    }

    public void onLoadMoreFinish() {
        if (hasNext()) {
            refreshLoadmore(LoadMoreBean.STATUS_READY);
        } else {
            onEndPage();
        }
    }

    public LoadMoreBean getTalentLoadmore() {
        return talentLoadmore;
    }

    private void onEndPage() {
        if (LoadMore.MODE_AUTO == loadMode()) {
            if (recyclerView != null && endScrollerListener != null) {
                recyclerView.removeOnScrollListener(endScrollerListener);
            }
        } else if (LoadMore.MODE_CLICK == loadMode()) {
            talentAdapter.publisher().deleteObserver(clickObserver);
        }
        refreshLoadmore(LoadMoreBean.STATUS_END);
    }

    private void refreshLoadmore(int status) {
        if (talentAdapter != null) {
            int loadMorePosition = talentAdapter.getItemCount() - 1;
            talentLoadmore.setStatus(status);
            talentAdapter.notifyItemChanged(loadMorePosition);
        }
    }
}
