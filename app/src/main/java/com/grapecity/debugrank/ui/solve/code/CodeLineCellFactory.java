package com.grapecity.debugrank.ui.solve.code;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodePresenter;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.FlexGridCanvasRenderEngine;
import com.grapecity.xuni.flexgrid.GridCellFactory;
import com.grapecity.xuni.flexgrid.GridCellRange;
import com.grapecity.xuni.flexgrid.GridCellType;
import com.grapecity.xuni.flexgrid.GridPanel;
import com.grapecity.xuni.flexgrid.GridRow;

import com.grapecity.debugrank.R;

/**
 * Created by chrisr on 1/27/2016.
 */
public class CodeLineCellFactory extends GridCellFactory
{
    private final ISolveCodePresenter<Spanned> solveCodePresenter;

    private TextView txtView;
    private int width;
    private int height;
    private int paddingLeft;

    public CodeLineCellFactory(FlexGrid flexGrid, ISolveCodePresenter<Spanned> solveCodePresenter)
    {
        super(flexGrid);

        this.solveCodePresenter = solveCodePresenter;

        txtView = new TextView(flexGrid.getContext());

        flexGrid.addView(txtView);

        txtView.setSingleLine(true);
        txtView.setVisibility(View.INVISIBLE);
        txtView.setTypeface(flexGrid.getFontTypeface());
        txtView.setTextSize(flexGrid.getFontSize());
        txtView.setTextColor(flexGrid.getSelectionFontColor());

        paddingLeft = Math.round(FlexGridCanvasRenderEngine.getDimensionSize(flexGrid.getCellPaddingLeft()));
    }

    @Override
    public void createCellContent(GridPanel gridPanel, FlexGridCanvasRenderEngine renderEngine, GridCellRange cellRange, Rect bounds)
    {
        if (gridPanel.getCellType() == GridCellType.CELL)
        {
            if (cellRange.col == 0)
            {
                //row is selected, use default coloring
                if (!flexGrid.getSelection().isValid() || !flexGrid.getSelection().containsRow(cellRange.row))
                {
                    flexGrid.renderEngine.setFillColor(Color.GRAY);
                }

                super.createCellContent(gridPanel, renderEngine, cellRange, bounds);
            }
            else if (cellRange.col == 1)
            {
                //editing this row, don't render the highlighted text
                if (!flexGrid.getEditRange().isValid() || !flexGrid.getEditRange().containsRow(cellRange.row))
                {
                    CodeLine codeLine = (CodeLine) gridPanel.getRows().get(cellRange.row).getDataItem();

                    if (width != bounds.width() && height != bounds.height())
                    {
                        width = bounds.width();
                        height = bounds.height();

                        txtView.setLayoutParams(new FrameLayout.LayoutParams(width - (paddingLeft * 2), height));
                    }

                    //row is selected, no syntax highlighting
                    if (flexGrid.getSelection().isValid() && flexGrid.getSelection().containsRow(cellRange.row))
                    {
                        txtView.setText(codeLine.getCodeText());
                    }
                    else
                    {
                        txtView.setText(this.solveCodePresenter.getSyntaxHighlightedText(codeLine));
                    }

                    //draw recycled TextView directly to FlexGrid canvas
                    renderEngine.canvas.save();
                    renderEngine.canvas.translate(bounds.left + renderEngine.panX + paddingLeft, bounds.top + renderEngine.panY);
                    txtView.draw(renderEngine.canvas);
                    renderEngine.canvas.restore();
                }
            }
        }
    }

    @Override
    public View createCellEditor(GridPanel gridPanel, GridCellRange cellRange, Rect bounds)
    {
        EditText editText = (EditText) super.createCellEditor(gridPanel, cellRange, bounds);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            editText.setBackground(null);
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            editText.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
        }

        editText.setBackgroundColor(ContextCompat.getColor(flexGrid.getContext(), R.color.colorTransparent));

        return editText;
    }
}