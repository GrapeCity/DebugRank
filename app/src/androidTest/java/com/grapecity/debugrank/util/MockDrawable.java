package com.grapecity.debugrank.util;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/**
 * Created by chrisripple on 4/26/16.
 */
public class MockDrawable extends Drawable
{
    private Object tag;

    public Object getTag()
    {
        return tag;
    }

    public void setTag(Object tag)
    {
        this.tag = tag;
    }

    @Override
    public void draw(Canvas canvas)
    {

    }

    @Override
    public void setAlpha(int alpha)
    {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter)
    {

    }

    @Override
    public int getOpacity()
    {
        return 0;
    }
}
