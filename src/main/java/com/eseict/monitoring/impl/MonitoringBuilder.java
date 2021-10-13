package com.eseict.monitoring.impl;

import com.eseict.monitoring.Monitoring;
import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.scheduler.CheckScheduler;
import com.eseict.monitoring.transpoter.Transporter;

public class MonitoringBuilder {
    private Checker checker;
    private CheckScheduler checkScheduler;

    public static MonitoringBuilder create() {
        return new MonitoringBuilder();
    }

    public final MonitoringBuilder setChecker(Checker checker) {
        this.checker = checker;
        return this;
    }

    public final MonitoringBuilder setCheckerScheduler(CheckScheduler checkScheduler) {
        this.checkScheduler = checkScheduler;
        return this;
    }

    public Monitoring build() {
        return new DefaultMonitoring(checkScheduler, checker);
    }
}
