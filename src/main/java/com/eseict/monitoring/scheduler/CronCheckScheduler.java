package com.eseict.monitoring.scheduler;

import com.eseict.monitoring.checker.Checker;

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
