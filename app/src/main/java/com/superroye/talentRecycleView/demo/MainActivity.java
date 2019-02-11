package com.superroye.talentRecycleView.demo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.superroye.talentrecyclerview.TalentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView listRv;
    TalentAdapter adapter;

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
        list.add(new MultiItem2(MultiItem2.TYPE_2, "Item"));
        list.add(new MultiItem2(MultiItem2.TYPE_3, "Item"));

        adapter.resetItems(list);
    }
}
