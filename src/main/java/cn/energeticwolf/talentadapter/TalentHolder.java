package cn.energeticwolf.talentadapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Field;

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
                            fields[i].set(this, view);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void toView();
}
