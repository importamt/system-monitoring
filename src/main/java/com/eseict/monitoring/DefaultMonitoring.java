package com.eseict.monitoring;

import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.scheduler.CheckScheduler;

public class DefaultMonitoring implements Monitoring {
    private CheckScheduler scheduler = null;

    @Override
    public void startMonitoring() {
        //Create scheduler
        scheduler = null;

        //Create checker
        Checker checker = null;

        //set checker to scheduler and start Scheduler
        scheduler.startSchedule(checker);
    }

    @Override
    public void stopMonitoring() {
        //Stop scheduler
        scheduler.stopSchedule();
    }
}
