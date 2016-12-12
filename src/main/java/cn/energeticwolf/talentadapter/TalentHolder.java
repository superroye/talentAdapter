package cn.energeticwolf.talentadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Roye on 2016/12/10.
 */

public abstract class TalentHolder<T> extends RecyclerView.ViewHolder {

    public T itemValue;

    public TalentHolder(View itemView) {
        super(itemView);
        initView();
    }

    public final void bind(T data) {
        itemValue = data;
        toView();
    }

    public abstract void initView();

    public abstract void toView();
}
