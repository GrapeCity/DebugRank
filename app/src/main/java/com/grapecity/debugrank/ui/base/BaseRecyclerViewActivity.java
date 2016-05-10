package com.grapecity.debugrank.ui.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.Bind;
import com.grapecity.debugrank.R;

/**
 * Created by chrisr on 3/31/2016.
 */
public abstract class BaseRecyclerViewActivity<T extends RecyclerView.Adapter> extends BaseActivity
{
    @Bind(R.id.recyclerView)
    protected RecyclerView recyclerView;
    protected T adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        hideFab();
    }

    @Override
    protected void attachView()
    {
        adapter = getRecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentAreaLayoutId()
    {
        return R.layout.content_recyclerview;
    }

    protected abstract T getRecyclerViewAdapter();
}
