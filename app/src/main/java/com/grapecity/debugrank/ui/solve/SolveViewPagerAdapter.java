package com.grapecity.debugrank.ui.solve;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisr on 2/11/2016.
 */
public class SolveViewPagerAdapter extends FragmentPagerAdapter
{
    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public SolveViewPagerAdapter(FragmentManager manager)
    {
        super(manager);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mFragmentTitleList.get(position);
    }

    public void updatePageTitle(int position, String newTitle)
    {
        mFragmentTitleList.set(position, newTitle);
        notifyDataSetChanged();
    }
}
