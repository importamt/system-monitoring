package com.eseict.monitoring.impl;

import com.eseict.monitoring.Monitoring;
import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.scheduler.CheckScheduler;

public class DefaultMonitoring implements Monitoring {
    private final CheckScheduler scheduler;
    private final Checker checker;

    public DefaultMonitoring(CheckScheduler scheduler, Checker checker) {
        this.scheduler = scheduler;
        this.checker = checker;
    }

    @Override
    public void startMonitoring() {
        //set checker to scheduler and start Scheduler
        scheduler.startSchedule(checker);
    }

    @Override
    public void stopMonitoring() {
        //Stop scheduler
        scheduler.stopSchedule();
    }
}
