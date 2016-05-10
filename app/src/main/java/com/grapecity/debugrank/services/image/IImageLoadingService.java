package com.grapecity.debugrank.services.image;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

/**
 * Created by chrisr on 3/31/2016.
 */
public interface IImageLoadingService
{
    void loadLanguageImage(ImageView imageView, String languageKey);

    void setDrawable(ImageView imageView, @DrawableRes int resourceId);
}
