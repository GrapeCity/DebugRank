package com.grapecity.debugrank.ui.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;

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
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        adapter = getRecyclerViewAdapter();
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                swipeRefreshLayout.setEnabled(linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });
    }

    @Override
    protected int getContentAreaLayoutId()
    {
        return R.layout.content_recyclerview;
    }

    protected abstract T getRecyclerViewAdapter();
}
