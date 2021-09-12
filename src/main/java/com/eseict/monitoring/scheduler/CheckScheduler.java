package com.eseict.monitoring.scheduler;

import com.eseict.monitoring.checker.Checker;

public interface CheckScheduler {

    void startSchedule(Checker checker);

    void stopSchedule();

}
