package com.supylc.talentrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

/**
 * @author Roye
 * @date 2019/4/18
 */
public class LoadMoreSupport implements ILoadMore {

    private ILoadMore loadMoreDelegate;
    private EndScrollerListener endScrollerListener;
    private RecyclerView recyclerView;
    private TalentAdapter talentAdapter;

    public LoadMoreSupport(TalentAdapter talentAdapter) {
        this.talentAdapter = talentAdapter;
    }

    public void setLoadMore(RecyclerView recyclerView, ILoadMore loadMore) {
        this.loadMoreDelegate = loadMore;
        this.recyclerView = recyclerView;
        if (endScrollerListener != null) {
            recyclerView.removeOnScrollListener(endScrollerListener);
        }
        endScrollerListener = new EndScrollerListener(loadMore);
        recyclerView.addOnScrollListener(endScrollerListener);
    }

    public boolean isNeedLoadmore() {
        return endScrollerListener != null;
    }

    @Override
    public void onLoadMore() {
        loadMoreDelegate.onLoadMore();
    }

    public void onEndPage() {
        onEndPage("没有更多了~");
    }

    public void onEndPage(String text) {
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(endScrollerListener);
        }
        if (talentAdapter != null) {
            int loadMorePosition = talentAdapter.getItemCount() - 1;
            Object item = talentAdapter.getItem(loadMorePosition);
            if (item instanceof TalentLoadmore) {
                ((TalentLoadmore) item).setHasMore(false);
                ((TalentLoadmore) item).setText(TextUtils.isEmpty(text) ? "" : text);
            }
            talentAdapter.notifyItemChanged(loadMorePosition);
        }
    }
}
