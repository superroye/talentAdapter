package com.supylc.talentrecyclerview;

import java.io.Serializable;

/**
 * @author Roye
 * @date 2019/4/17
 */
public class TalentLoadmore implements Serializable {

    public TalentLoadmore() {
        this.hasMore = true;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private boolean hasMore;
    private String text;

}
