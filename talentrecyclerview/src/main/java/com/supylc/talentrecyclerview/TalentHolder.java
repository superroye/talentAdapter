package com.supylc.talentrecyclerview;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.supylc.talentrecyclerview.support.Publisher;
import com.supylc.talentrecyclerview.tools.LifecycleUtils;

import java.lang.reflect.Field;

/**
 * Created by Roye on 2016/12/10.
 */
public abstract class TalentHolder<T> extends RecyclerView.ViewHolder {

    String TAG = TalentHolder.class.getSimpleName();

    private Publisher publisher;
    public T itemValue;
    public OnItemClickListener mItemClickListener;

    public TalentHolder(View itemView) {
        super(itemView);
        initView();
    }

    public final void bind(T data) {
        itemValue = data;
        if (!LifecycleUtils.isContextAvailable(itemView.getContext())) {
            Log.w(TAG, "activity is finishing or destroyed.");
            return;
        }
        toView();
    }

    public void initView() {
        Field[] fields = getClass().getDeclaredFields();
        if (fields != null) {
            Resources res = itemView.getResources();
            String packageName = itemView.getContext().getPackageName();
            try {
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].getAnnotation(AutoView.class) != null) {
                        String viewName = fields[i].getName();
                        View view = itemView.findViewById(res.getIdentifier(viewName, "id", packageName));
                        if (view != null) {
                            fields[i].setAccessible(true);
                            fields[i].set(this, view);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected void setOnItemClick(OnItemClickListener listener) {
        mItemClickListener = listener;

        if (listener == null || itemView.hasOnClickListeners()) {//不覆盖item的原来click事件
            return;
        }

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentHolderInfo holderInfo = new TalentHolderInfo(TalentHolder.this.getClass(), itemValue, getAdapterPosition());
                mItemClickListener.onItemClickListener(v, holderInfo);
            }
        });
    }

    protected void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void post(Object value) {
        if (publisher != null) {
            publisher.postValue(this.getClass(), itemValue, value);
        }
    }

    //局部刷新
    public void onPayload(Object payload) {

    }

    public <T extends View> T findV(int resId) {
        return (T) itemView.findViewById(resId);
    }

    public abstract void toView();

    public void recycle() {
    }
}
