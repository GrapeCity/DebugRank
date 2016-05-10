package com.grapecity.debugrank.ui.misc;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * http://stackoverflow.com/questions/9650265/how-do-disable-paging-by-swiping-with-finger-in-viewpager-but-still-be-able-to-s
 * <p/>
 * Created by chrisr on 2/11/2016.
 */
public class NonSwipeableViewPager extends ViewPager
{
    public NonSwipeableViewPager(Context context)
    {
        super(context);
    }

    public NonSwipeableViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event)
    {
        // Never allow swiping to switch between pages
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        // Never allow swiping to switch between pages
        return false;
    }
}