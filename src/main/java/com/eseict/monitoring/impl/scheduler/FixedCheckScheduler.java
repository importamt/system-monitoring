package com.eseict.monitoring.impl.scheduler;

import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.scheduler.CheckScheduler;

import java.util.Timer;
import java.util.TimerTask;

public class FixedCheckScheduler implements CheckScheduler {

    private int delay = 30000;
    private int initialDelay = 0;
    private Timer timer;
    private TimerTask timerTask;

    public FixedCheckScheduler() {
    }

    public FixedCheckScheduler(int delay) {
        this.delay = delay;
    }

    public FixedCheckScheduler(int delay, int initialDelay) {
        this.delay = delay;
        this.initialDelay = initialDelay;
    }

    @Override
    public void startSchedule(Checker checker) {
        timerTask = new TimerTask() {
            @Override
            public void run() {
                checker.check();
            }
        };

        timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, initialDelay, delay);
    }

    @Override
    public void stopSchedule() {
        timer.cancel();
    }
}
