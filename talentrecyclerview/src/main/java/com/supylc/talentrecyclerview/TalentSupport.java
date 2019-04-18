package com.supylc.talentrecyclerview;

/**
 * @author Roye
 * @date 2019/4/17
 */
public class TalentSupport {

    private NullObject nullObject;
    private TalentLoadmore talentLoadmore;
    private LoadMoreSupport loadMoreSupport;

    public TalentSupport(TalentAdapter talentAdapter) {
        this.nullObject = new NullObject();
        this.talentLoadmore = new TalentLoadmore();
        talentAdapter.registerHolder(NullHolder.class);
        talentAdapter.registerHolder(LoadmoreHolder.class);

        loadMoreSupport = new LoadMoreSupport(talentAdapter);
    }

    public LoadMoreSupport getLoadMoreSupport() {
        return loadMoreSupport;
    }

    public NullObject getNullObject() {
        return nullObject;
    }

    public TalentLoadmore getTalentLoadmore() {
        return talentLoadmore;
    }


}
