package com.grapecity.debugrank.ui.puzzles;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.ProgrammingLanguage;
import com.grapecity.debugrank.javalib.entities.Puzzle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.grapecity.debugrank.R;
import com.grapecity.debugrank.services.image.IImageLoadingService;

/**
 * Created by Ripple on 1/25/2016.
 */
public class PuzzlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final View.OnClickListener onClickListener;
    private final IImageLoadingService imageLoadingService;

    private List<Puzzle> list = new ArrayList<>();
    private final Resources resources;
    private Map<String, CompletedPuzzle> completedPuzzleMap;

    private final ProgrammingLanguage programmingLanguage;

    private final SimpleDateFormat completedDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public class ViewHolderItem extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        Puzzle puzzle;

        @Bind(R.id.puzzleParentLayout)
        LinearLayout parentLayout;

        @Bind(R.id.puzzleName)
        TextView name;

        @Bind(R.id.points)
        TextView points;

        @Bind(R.id.minutes)
        TextView minutes;

        @Bind(R.id.bugs)
        TextView bugs;

        @Bind(R.id.solved)
        TextView solved;

        View parent;

        public ViewHolderItem(View v)
        {
            super(v);

            parent = ((ViewGroup) v).getChildAt(0);

            ButterKnife.bind(this, parent);
        }

        public void bind(Puzzle puzzle, String solvedDate, Resources resources)
        {
            this.puzzle = puzzle;

            int minute = (int) Math.floor(puzzle.time / 60);

            name.setText(puzzle.name);
            points.setText(String.format(resources.getString(R.string.points), puzzle.points));
            minutes.setText(String.format(resources.getString(minute > 1 ? R.string.minutes : R.string.minute), minute));
            bugs.setText(String.format(resources.getString(puzzle.bugs > 1 ? R.string.bugs : R.string.bug), puzzle.bugs));

            if (solvedDate != null)
            {
                solved.setText(String.format("%s %s", resources.getString(R.string.solved), solvedDate));
            } else
            {
                solved.setVisibility(View.GONE);
            }

            parent.setTag(puzzle);
        }

        @Override
        public void onClick(View v)
        {
            if (!completedPuzzleMap.containsKey(puzzle.key))
            {
                onClickListener.onClick(parent);
            }
        }
    }

    public class ViewHolderHeader extends RecyclerView.ViewHolder
    {
        protected ProgrammingLanguage programmingLanguage;

        @Bind(R.id.languageIcon)
        protected ImageView icon;

        @Bind(R.id.languageName)
        protected TextView text;


        public ViewHolderHeader(View v)
        {
            super(v);

            ButterKnife.bind(this, v);
        }

        public void bind(ProgrammingLanguage language)
        {
            this.programmingLanguage = language;

            imageLoadingService.loadLanguageImage(icon, language.key);

            text.setText(language.name);


        }
    }

    public PuzzlesAdapter(View.OnClickListener onClickListener, Resources resources, ProgrammingLanguage programmingLanguage, IImageLoadingService imageLoadingService)
    {
        super();

        this.onClickListener = onClickListener;
        this.programmingLanguage = programmingLanguage;
        this.resources = resources;
        this.imageLoadingService = imageLoadingService;
    }

    public void setCompletedPuzzleMap(Map<String, CompletedPuzzle> completedPuzzleMap)
    {
        this.completedPuzzleMap = completedPuzzleMap;
        notifyDataSetChanged();
    }

    public void setPuzzles(List<Puzzle> puzzles)
    {
        this.list = puzzles;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;

        if (viewType == TYPE_ITEM)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_puzzle, parent, false);

            viewHolder = new ViewHolderItem(v);
        } else if (viewType == TYPE_HEADER)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_language_header, parent, false);

            viewHolder = new ViewHolderHeader(v);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof ViewHolderItem)
        {
            holder.itemView.setOnClickListener(((ViewHolderItem) holder));

            String solvedDate = null;

            Puzzle puzzle = list.get((position - 1));

            if (completedPuzzleMap.containsKey(puzzle.key))
            {
                solvedDate = completedDateFormat.format(completedPuzzleMap.get(puzzle.key).getDate());
            }

            ((ViewHolderItem) holder).bind(puzzle, solvedDate, resources);
        } else if (holder instanceof ViewHolderHeader)
        {
            ((ViewHolderHeader) holder).bind(programmingLanguage);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position == 0)
        {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount()
    {
        return list != null ? list.size() + 1 : 0;
    }
}
