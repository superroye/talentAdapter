package com.supylc.talentrecyclerview.support;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author Roye
 * @date 2019/4/17
 */
public class EndScrollerListener extends RecyclerView.OnScrollListener {

    private int minLeftItemCount = 1;//剩余多少条时开始加载更多
    private LoadMore loadMore;

    public EndScrollerListener(LoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int itemCount = layoutManager.getItemCount();
            int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
            if (lastPosition == layoutManager.getItemCount() - 1) {//容错处理，保证滑到最后一条时一定可以加载更多
                loadMore.onLoadMore();
            } else {
                if (itemCount > minLeftItemCount) {
                    if (lastPosition == itemCount - minLeftItemCount) {
                        //一定要意识到，onScrolled方法并不是一直被回调的，估计最多一秒钟几十次
                        //所以当此条件满足时，可能并没有回调onScrolled方法，也就不会调用onLoadMore方法
                        //所以一定要想办法弥补这隐藏的bug，最简单的方式就是当滑到最后一条时一定可以加载更多
                        loadMore.onLoadMore();
                    }
                } else {//（第一次进入时）如果总数特别少，直接加载更多
                    loadMore.onLoadMore();
                }
            }
        }
    }

}
