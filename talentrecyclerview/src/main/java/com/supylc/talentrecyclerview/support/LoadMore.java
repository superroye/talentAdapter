package com.supylc.talentrecyclerview.support;

/**
 * @author Roye
 * @date 2019/4/17
 */
public interface LoadMore {

    public static final int MODE_CLICK = 1;
    public static final int MODE_AUTO = 2;

    int loadMode();

    void onLoadMore();

    boolean hasNext();
}
