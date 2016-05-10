package com.grapecity.debugrank.ui.solve.result;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.grapecity.debugrank.MyApp;
import com.grapecity.debugrank.R;

import com.grapecity.debugrank.javalib.entities.AggregatedBugsPoints;
import com.grapecity.debugrank.javalib.entities.CodeLine;
import com.grapecity.debugrank.javalib.entities.CompletedPuzzle;
import com.grapecity.debugrank.javalib.entities.TestCaseResult;
import com.grapecity.debugrank.javalib.ui.solve.ISolveView;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultPresenter;
import com.grapecity.debugrank.javalib.ui.solve.result.ISolveResultView;

import com.grapecity.debugrank.services.image.IImageLoadingService;
import com.grapecity.debugrank.ui.base.BaseFragment;

/**
 * Created by Ripple on 1/25/2016.
 */
public class SolveResultFragment extends BaseFragment implements ISolveResultView
{
    @Inject
    ISolveResultPresenter solveResultPresenter;

    @Bind(R.id.recyclerView)
    RecyclerView testCaseRecyclerView;

    @Inject
    IImageLoadingService imageLoadingService;

    TestCaseAdapter testCaseAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View root = inflater.inflate(R.layout.content_recyclerview, container, false);

        ButterKnife.bind(this, root);

        ((MyApp) (getActivity()).getApplication()).getSolveResultComponent().inject(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        testCaseRecyclerView.setLayoutManager(linearLayoutManager);

        testCaseAdapter = new TestCaseAdapter(getActivity(), imageLoadingService);

        testCaseRecyclerView.setAdapter(testCaseAdapter);

        solveResultPresenter.attachView(this);
        solveResultPresenter.attachParentView((ISolveView) getActivity());

        return root;
    }

    @Override
    public void codeCompiling()
    {

    }

    @Override
    public void codeCompiled(List<TestCaseResult> result, boolean passed, int numberPassedTestCases)
    {
        testCaseAdapter.codeCompiled(result);
    }

    @Override
    public void puzzleSolved(CompletedPuzzle result)
    {

    }

    @Override
    public void compileCode(List<CodeLine> codeLines)
    {
        solveResultPresenter.compileCode(codeLines);
    }

    @Override
    public void aggregatedBugPointsLoaded(AggregatedBugsPoints aggregatedBugsPoints)
    {

    }
}
