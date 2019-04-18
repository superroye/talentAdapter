package com.supylc.talentRecycleView.demo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.supylc.talentrecyclerview.EndScrollerListener;
import com.supylc.talentrecyclerview.ILoadMore;
import com.supylc.talentrecyclerview.TalentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView listRv;
    TalentAdapter adapter;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        listRv = findViewById(R.id.recycler_view);

        adapter = new TalentAdapter();
        adapter.registerHolder(Holder1.class);
        adapter.registerHolder(Holder2.class, MultiItem2.TYPE_1);
        adapter.registerHolder(Holder3.class, MultiItem2.TYPE_2);
        adapter.registerHolder(Holder3.class, MultiItem2.TYPE_3);

        listRv.setAdapter(adapter);

        adapter.getLoadMoreSupport().setLoadMore(listRv, new ILoadMore() {

            @Override
            public void onLoadMore() {
                loadData();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        loadData();
    }

    void loadData() {
        List list = new ArrayList<>();
        list.add(new Item1("Item"));
        list.add(new MultiItem2(MultiItem2.TYPE_1, "Item"));
        list.add(new Item1("Item"));
        list.add(new MultiItem2(MultiItem2.TYPE_2, "Item"));
        list.add(new Item1("Item"));
        list.add(new MultiItem2(MultiItem2.TYPE_2, "Item2"));
        list.add(new MultiItem2(MultiItem2.TYPE_3, "Item3"));
        list.add(null);
        list.add(new MultiItem2(MultiItem2.TYPE_3, "Item3"));
        list.add(new MultiItem2(MultiItem2.TYPE_2, "Item2"));
        list.add(new MultiItem2(MultiItem2.TYPE_3, "Item3"));
        list.add(new MultiItem2(MultiItem2.TYPE_2, "Item2"));
        list.add(new MultiItem2(MultiItem2.TYPE_3, "Item3"));

        adapter.resetItems(list);

        if (count++ > 5) {
            adapter.getLoadMoreSupport().onEndPage();
        }
    }
}
