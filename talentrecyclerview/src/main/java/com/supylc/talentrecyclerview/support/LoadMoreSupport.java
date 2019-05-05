package com.supylc.talentrecyclerview.support;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
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
    private TalentAdapter adapter;
    private LoadMoreBean talentLoadmore;
    private Subscriber clickObserver;

    public LoadMoreSupport(TalentAdapter talentAdapter) {
        this.adapter = talentAdapter;
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
            adapter.publisher().subscribe(clickObserver);
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
        if (adapter.getItemCount() <= 1) {
            return;
        }
        if (hasNext()) {
            refreshLoadmore(LoadMoreBean.STATUS_LOADING);
            loadMoreDelegate.onLoadMore();
        } else {
            onEndPage();
        }
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
        refreshLoadmore(LoadMoreBean.STATUS_END);
    }

    public synchronized void resetLoadmoreStatus() {
        if (hasNext()) {
            talentLoadmore.setStatus(LoadMoreBean.STATUS_READY);
        }
    }

    private synchronized void refreshLoadmore(int status) {
        if (adapter != null) {
            talentLoadmore.setStatus(status);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyItemChanged(adapter.getItemCount() - 1);
                }
            });
        }
    }

    public void onItemsChange() {
        if (recyclerView != null && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1) {
                        onLoadMore();
                    }
                }
            });
        }
    }
}
