package com.grapecity.debugrank.ui.solve.result;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.grapecity.debugrank.R;
import com.grapecity.debugrank.services.image.IImageLoadingService;

import com.grapecity.debugrank.javalib.entities.TestCaseResult;

/**
 * Created by chrisr on 3/21/2016.
 */
public class TestCaseAdapter extends RecyclerView.Adapter<TestCaseAdapter.ViewHolder>
{
    private List<TestCaseResult> testCaseResultList = new ArrayList<>();

    private final String testCaseString;

    private IImageLoadingService imageLoadingService;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.passed)
        ImageView passed;

        @Bind(R.id.testcase)
        TextView testCase;

        @Bind(R.id.inputLabel)
        TextView inputLabel;

        @Bind(R.id.input)
        TextView input;

        @Bind(R.id.outputLabel)
        TextView outputLabel;

        @Bind(R.id.output)
        TextView output;

        @Bind(R.id.expectedOutputLabel)
        TextView expectedOutputLabel;

        @Bind(R.id.expectedOutput)
        TextView expectedOutput;

        @Bind(R.id.messageLabel)
        TextView messageLabel;

        @Bind(R.id.message)
        TextView message;

        public ViewHolder(View v)
        {
            super(v);

            ButterKnife.bind(this, ((ViewGroup) v).getChildAt(0));
        }

        public void bind(TestCaseResult testCaseResult, String testCaseString, IImageLoadingService imageLoadingService)
        {
            //compile error message
            if (testCaseResult.isCompileError())
            {
                showCompileErrorMode(true);

                testCase.setText(R.string.compile_error);
                message.setText(testCaseResult.message);
            }
            //code compiled and here are test case results
            else
            {
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i < testCaseResult.input.length; i++)
                {
                    stringBuilder.append(testCaseResult.input[i]);

                    if (i != testCaseResult.input.length - 1)
                    {
                        stringBuilder.append("\n");
                    }
                }

                showCompileErrorMode(false);

                input.setText(stringBuilder.toString());
                output.setText(testCaseResult.output);
                expectedOutput.setText(testCaseResult.expectedOutput);
                testCase.setText(String.format("%s %d", testCaseString, testCaseResult.testCase));

                message.setText(testCaseResult.message);

                imageLoadingService.setDrawable(passed, testCaseResult.passed ? R.drawable.ic_done_green_24dp : R.drawable.ic_clear_red_24dp);
            }
        }

        private void showCompileErrorMode(boolean showCompileErrorMode)
        {
            int visibility = showCompileErrorMode ? View.GONE : View.VISIBLE;

            passed.setVisibility(visibility);
            inputLabel.setVisibility(visibility);
            input.setVisibility(visibility);
            outputLabel.setVisibility(visibility);
            output.setVisibility(visibility);
            expectedOutputLabel.setVisibility(visibility);
            expectedOutput.setVisibility(visibility);
            messageLabel.setVisibility(visibility);
        }
    }

    public TestCaseAdapter(Context context, IImageLoadingService imageLoadingService)
    {
        testCaseString = context.getResources().getString(R.string.test_case_num);
        this.imageLoadingService = imageLoadingService;
    }

    public void codeCompiled(List<TestCaseResult> result)
    {
        this.testCaseResultList = result;
        notifyDataSetChanged();
    }

    @Override
    public TestCaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_testcase, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(TestCaseAdapter.ViewHolder holder, int position)
    {
        holder.bind(testCaseResultList.get(position), testCaseString, imageLoadingService);
    }

    @Override
    public int getItemCount()
    {
        return testCaseResultList.size();
    }
}
