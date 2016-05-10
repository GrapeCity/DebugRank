package com.grapecity.debugrank.services.image;

import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import com.grapecity.debugrank.javalib.constants.Api;

import com.grapecity.debugrank.R;

/**
 * Created by chrisr on 3/31/2016.
 */
public class PicassoGithubImageLoadingService implements IImageLoadingService
{
    @Override
    public void loadLanguageImage(ImageView imageView, String languageKey)
    {
        //clear content first so that recycled RecyclerView items don't show old images.
        imageView.setImageDrawable(null);

        Picasso.with(imageView.getContext()).load(String.format(Api.GITHUB_API_IMAGE_URL, languageKey)).into(imageView);
    }

    @Override
    public void setDrawable(ImageView imageView, @DrawableRes int resourceId)
    {
        imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), resourceId));
    }
}