package com.eseict.monitoring.impl.scheduler;

import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.scheduler.CheckScheduler;

public class CronCheckScheduler implements CheckScheduler {
    @Override
    public void startSchedule(Checker checker) {


        //Schedule's do work...
        checker.check();


    }

    @Override
    public void stopSchedule() {

    }
}
