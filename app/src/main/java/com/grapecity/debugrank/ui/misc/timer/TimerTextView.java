package com.grapecity.debugrank.ui.misc.timer;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.util.AttributeSet;

import java.util.concurrent.atomic.AtomicBoolean;

import com.grapecity.debugrank.ui.misc.TextViewDrawableSize;

/**
 * Created by chrisr on 2/12/2016.
 */
public class TimerTextView extends TextViewDrawableSize
{
    private int timeInSeconds;
    private int minutes;
    private int seconds;

    private ITimerCompletedListener timerCompletedListener;

    private CountDownTimer countDownTimer;

    private AtomicBoolean canUpdate = new AtomicBoolean(true);

    public TimerTextView(Context context)
    {
        super(context);
        init(context, null, 0, 0);
    }

    public TimerTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public TimerTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(value = Build.VERSION_CODES.LOLLIPOP)
    public TimerTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        //consume custom attributes here
    }

    public void setOnCompletedListener(ITimerCompletedListener timerCompletedListener)
    {
        this.timerCompletedListener = timerCompletedListener;
    }

    public void setTimeInSeconds(int timeInSeconds)
    {
        this.timeInSeconds = timeInSeconds;

        setTimeText();
    }

    public void startTimer()
    {
        countDownTimer = new CountDownTimer(timeInSeconds * 1000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                if (canUpdate.get())
                {
                    timeInSeconds = (int) Math.floor(millisUntilFinished / 1000);

                    setTimeText();
                }
            }

            @Override
            public void onFinish()
            {
                timeInSeconds = 0;
                setTimeText();
                timerCompletedListener.timeRanOut();
            }
        }.start();
    }

    public void stopTimer()
    {
        canUpdate.set(false);

        if(countDownTimer != null)
        {
            countDownTimer.cancel();
        }
    }

    public boolean isCompleted()
    {
        return timeInSeconds == 0;
    }

    private void setTimeText()
    {
        if (canUpdate.get())
        {
            minutes = (int) Math.floor(timeInSeconds / 60);
            seconds = timeInSeconds % 60;

            if (seconds < 10)
            {
                setText(String.format("%d:0%d", minutes, seconds));
            }
            else
            {
                setText(String.format("%d:%d", minutes, seconds));
            }
        }
    }
}
