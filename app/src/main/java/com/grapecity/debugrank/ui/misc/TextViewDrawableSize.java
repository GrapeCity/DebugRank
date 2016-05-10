package com.grapecity.debugrank.ui.misc;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.grapecity.debugrank.R;

/**
 * Created by chrisr on 1/26/2016.
 */
public class TextViewDrawableSize extends TextView
{
    private int mDrawableWidth;
    private int mDrawableHeight;

    public TextViewDrawableSize(Context context)
    {
        super(context);
        init(context, null, 0, 0);
    }

    public TextViewDrawableSize(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public TextViewDrawableSize(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
    public TextViewDrawableSize(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextViewDrawableSize, defStyleAttr, defStyleRes);

        try
        {
            mDrawableWidth = array.getDimensionPixelSize(R.styleable.TextViewDrawableSize_compoundDrawableWidth, -1);
            mDrawableHeight = array.getDimensionPixelSize(R.styleable.TextViewDrawableSize_compoundDrawableHeight, -1);
        } finally
        {
            array.recycle();
        }

        if (mDrawableWidth > 0 || mDrawableHeight > 0)
        {
            initCompoundDrawableSize();
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom)
    {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);

        initCompoundDrawableSize();
    }

    private void initCompoundDrawableSize()
    {
        Drawable[] drawables = getCompoundDrawables();
        for (Drawable drawable : drawables)
        {
            if (drawable == null)
            {
                continue;
            }

            Rect realBounds = drawable.getBounds();
            float scaleFactor = realBounds.height() / (float) realBounds.width();

            float drawableWidth = realBounds.width();
            float drawableHeight = realBounds.height();

            if (mDrawableWidth > 0)
            {
                // save scale factor of image
                if (drawableWidth > mDrawableWidth)
                {
                    drawableWidth = mDrawableWidth;
                    drawableHeight = drawableWidth * scaleFactor;
                }
            }
            if (mDrawableHeight > 0)
            {
                // save scale factor of image
                if (drawableHeight > mDrawableHeight)
                {
                    drawableHeight = mDrawableHeight;
                    drawableWidth = drawableHeight / scaleFactor;
                }
            }

            realBounds.right = realBounds.left + Math.round(drawableWidth);
            realBounds.bottom = realBounds.top + Math.round(drawableHeight);

            drawable.setBounds(realBounds);
        }
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
    }
}