package com.supylc.talentrecyclerview.support;

import java.io.Serializable;

/**
 * @author Roye
 * @date 2019/4/17
 */
public class LoadMoreBean implements Serializable {

    public static final int STATUS_READY = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_END = 3;

    public LoadMoreBean() {
        this.status = STATUS_READY;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
}
