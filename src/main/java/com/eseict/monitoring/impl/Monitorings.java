package com.eseict.monitoring.impl;

import com.eseict.monitoring.Monitoring;
import com.eseict.monitoring.check.Check;
import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.impl.checker.DefaultChecker;
import com.eseict.monitoring.impl.scheduler.FixedCheckScheduler;
import com.eseict.monitoring.impl.transpoter.HttpTransporter;
import com.eseict.monitoring.impl.transpoter.RinoTransporter;

import java.sql.Timestamp;

public class Monitorings {

    public static Monitoring createDefault(
            String systemId,
            Check selfCheck,
            String retrieveSystemsUrl,
            String retrieveLinksUrl,
            String registerCheckResultUrl
    ) {
        MonitoringBuilder monitoringBuilder = MonitoringBuilder.create();
        HttpTransporter httpTransporter = new HttpTransporter(retrieveSystemsUrl, retrieveLinksUrl, registerCheckResultUrl);
        monitoringBuilder.setChecker(new DefaultChecker(systemId, selfCheck, httpTransporter))
                .setCheckerScheduler(new FixedCheckScheduler());

        return monitoringBuilder.build();
    }

    public static Monitoring createRino(
            String systemId,
            String systemKey,
            String secret,
            Check selfCheck,
            String omsUrl
    ) {
        MonitoringBuilder monitoringBuilder = MonitoringBuilder.create();
        RinoTransporter rinoTransporter = new RinoTransporter(omsUrl, systemKey, secret);
        monitoringBuilder
                .setChecker(new DefaultChecker(systemId, selfCheck, rinoTransporter))
                .setCheckerScheduler(new FixedCheckScheduler());

        return monitoringBuilder.build();
    }
}
