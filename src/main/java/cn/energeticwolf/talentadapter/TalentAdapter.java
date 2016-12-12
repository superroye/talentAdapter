package cn.energeticwolf.talentadapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roye on 2016/12/10.
 */

public class TalentAdapter extends RecyclerView.Adapter {

    String TAG = TalentAdapter.class.getSimpleName();

    List mItems = new ArrayList<>();

    List<Class> providers = new ArrayList<>();
    List<Class> clss = new ArrayList<>();

    public TalentAdapter() {
        this(null);
    }

    public TalentAdapter(List items) {
        if (items != null) {
            mItems.addAll(items);
        }
    }

    public List getItems() {
        return mItems;
    }

    public void resetItems(List items) {
        resetItems(items, true);
    }

    public void resetItems(List items, boolean notify) {
        if (items != null) {
            List list = new ArrayList<>();
            list.addAll(items);
            mItems = list;
            if (notify) {
                notifyDataSetChanged();
            }
        }
    }

    LayoutInflater inflater;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }

        Class entityClass = providers.get(viewType);
        Object holder = null;
        try {
            HolderRes holderRes = (HolderRes) entityClass.getAnnotation(HolderRes.class);
            View root = LayoutInflater.from(parent.getContext()).inflate(holderRes.value(), parent, false);
            holder = entityClass.getConstructor(View.class).newInstance(root);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return (TalentHolder) holder;
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = mItems.get(position);
        int index = clss.indexOf(obj.getClass());
        if (index > -1) {
            return index;
        } else {
            Log.e(TAG, "have not supported type.");
        }
        return 0;
    }

    public Class getDataType(Class holderClass) {
        Class cls = null;
        try {
            cls = (Class) (((ParameterizedType) holderClass.getGenericSuperclass()).getActualTypeArguments()[0]);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return cls;
    }

    public void addHolderType(Class<? extends TalentHolder> holderClass) {
        if (providers.contains(holderClass)) {
            Log.e(TAG, "reduplicate holder type.");
        } else {
            Class cls = getDataType(holderClass);
            if (clss.contains(cls)) {
                Log.e(TAG, "reduplicate holder DataType.");
            } else {
                clss.add(cls);
                providers.add(holderClass);
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TalentHolder) holder).bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
