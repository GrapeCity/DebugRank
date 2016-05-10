package com.grapecity.debugrank.ui.languages;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.grapecity.debugrank.R;
import com.grapecity.debugrank.services.image.IImageLoadingService;

/**
 * Created by Ripple on 1/25/2016.
 */
public class LanguagesAdapter extends RecyclerView.Adapter<LanguagesAdapter.ViewHolder>
{
    private final IImageLoadingService imageLoadingService;
    private final View.OnClickListener onClickListener;

    private List<ProgrammingLanguage> list = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        protected ProgrammingLanguage programmingLanguage;

        protected View parent;

        @Bind(R.id.languageIcon)
        protected ImageView icon;

        @Bind(R.id.languageName)
        protected TextView text;

        public ViewHolder(View v)
        {
            super(v);

            parent = ((ViewGroup) v).getChildAt(0);

            ButterKnife.bind(this, parent);
        }

        public void bind(ProgrammingLanguage language)
        {
            this.programmingLanguage = language;

            imageLoadingService.loadLanguageImage(icon, language.key);

            text.setText(language.name);

            parent.setTag(programmingLanguage);
        }
        @Override
        public void onClick(View view)
        {
            onClickListener.onClick(parent);
        }
    }

    public LanguagesAdapter(View.OnClickListener onClickListener, IImageLoadingService imageLoadingService)
    {
        super();

        this.onClickListener = onClickListener;
        this.imageLoadingService = imageLoadingService;
    }

    public void setLanguages(List<ProgrammingLanguage> languages)
    {
        this.list = languages;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_language, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.itemView.setOnClickListener(holder);
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
