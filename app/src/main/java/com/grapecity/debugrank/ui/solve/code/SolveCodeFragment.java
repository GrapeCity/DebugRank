package com.grapecity.debugrank.ui.solve.code;

import android.os.Bundle;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodePresenter;
import com.grapecity.debugrank.javalib.ui.solve.code.ISolveCodeView;
import com.grapecity.xuni.core.IEventHandler;
import com.grapecity.xuni.core.render.CanvasRenderEngine;
import com.grapecity.xuni.flexgrid.FlexGrid;
import com.grapecity.xuni.flexgrid.GridCellRangeEventArgs;
import com.grapecity.xuni.flexgrid.GridColumn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.grapecity.debugrank.MyApp;
import com.grapecity.debugrank.R;
import com.grapecity.debugrank.ui.base.BaseFragment;
import com.grapecity.debugrank.ui.solve.SolveActivity;

/**
 * Created by Ripple on 1/25/2016.
 */
public class SolveCodeFragment extends BaseFragment implements ISolveCodeView<Spanned>
{
    @Inject
    ISolveCodePresenter solveCodePresenter;

    @Bind(R.id.flexgrid)
    FlexGrid flexGrid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View root = inflater.inflate(R.layout.fragment_solve_code, container, false);

        ButterKnife.bind(this, root);

        ((MyApp) (getActivity()).getApplication()).getSolveCodeComponent().inject(this);

        flexGrid.setCellFactory(new CodeLineCellFactory(flexGrid, solveCodePresenter));

        flexGrid.getBeginningEdit().addHandler(flexGridStartEditing, flexGrid);
        flexGrid.getCellEditEnded().addHandler(flexGridFinishEditing, flexGrid);

        solveCodePresenter.attachView(this);
        solveCodePresenter.attachParentView((ISolveView) getActivity());

        solveCodePresenter.loadCode();

        return root;
    }

    @Override
    public void loadCode()
    {
        solveCodePresenter.loadCode();
    }

    @Override
    public void codeLoaded(List<CodeLine> list)
    {
        //add 10 empty lines at the end so there's enough padding on the bottom for the FAB and keyboard
        for(int i = 0; i < 10; i++)
        {
            list.add(new CodeLine(list.size() + 1, ""));
        }

        flexGrid.setItemsSource(list);

        GridColumn lineNumber = new GridColumn(flexGrid, "", "lineNumber");
        lineNumber.setReadOnly(true);

        GridColumn codeText = new GridColumn(flexGrid, "", "codeText");

        GridColumn dummy = new GridColumn(flexGrid, "", "");
        dummy.setReadOnly(true);
        dummy.setWidth(20);

        flexGrid.getColumns().add(lineNumber);
        flexGrid.getColumns().add(codeText);
        flexGrid.getColumns().add(dummy);

        //freeze line numbers on the left side
        flexGrid.setFrozenColumns(1);

        //auto size the code column
        flexGrid.autoSizeColumns(0, 1);

        //add some padding to ensure our custom cell factory has enough room for the highlighted syntax
        codeText.setWidthNoDPScale(Math.round(codeText.getWidth() + CanvasRenderEngine.getDimensionSize(10)));

        dummy.setWidth("*");

        //Code is loaded, start the timer
        ((SolveActivity) getActivity()).solveCodeLoaded();
    }

    @Override
    public List<CodeLine> getCodeToCompile()
    {
        List<CodeLine> codeLines = new ArrayList<>(flexGrid.getItemsSource().size());

        codeLines.addAll((Collection<CodeLine>) flexGrid.getItemsSource());

        return codeLines;
    }

    @Override
    public void syntaxHighlightedTextRefreshed(Spanned displayValue)
    {
        //just force a redraw so it gets new syntax from cache
        flexGrid.invalidate();
    }

    @Override
    public void aggregatedBugPointsLoaded(AggregatedBugsPoints aggregatedBugsPoints)
    {
        //TODO: remove somehow
    }

    @Override
    public void codeCompiling()
    {
        //set flexGrid to readonly
        flexGrid.setReadOnly(true);
    }

    @Override
    public void codeCompiled(List<TestCaseResult> result, boolean passed, int numberPassedTestCases)
    {
        if(!passed)
        {
            //set flexGrid to readonly
            flexGrid.setReadOnly(false);
        }
    }

    @Override
    public void puzzleSolved(CompletedPuzzle result)
    {
        //set flexGrid to readonly
        flexGrid.setReadOnly(true);
    }

    private final IEventHandler flexGridStartEditing = new IEventHandler()
    {
        @Override
        public void call(Object o, Object o1)
        {
            solveCodePresenter.beginEditing(getEditingCodeLine(o1));
        }
    };

    private final IEventHandler flexGridFinishEditing = new IEventHandler()
    {
        @Override
        public void call(Object o, Object o1)
        {
            solveCodePresenter.finishEditing(getEditingCodeLine(o1));
        }
    };

    private CodeLine getEditingCodeLine(Object o1)
    {
        GridCellRangeEventArgs eventArgs = (GridCellRangeEventArgs) o1;

        return (CodeLine) flexGrid.getItemsSource().get(eventArgs.getRow());
    }
}
