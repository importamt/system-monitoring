package com.eseict.monitoring.impl;

import com.eseict.monitoring.Monitoring;
import com.eseict.monitoring.dto.CheckResult;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class MonitoringsTest {

    @Test
    void test_rino_monitoring(){
        Monitoring monitoring = Monitorings.createRino(
                "cvUvb~Sc4BH9keO7HopXfM",
                "", "",
                (sourceId, targetId) -> {
                    long currentTime = System.currentTimeMillis();
                    return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, "SUCCESS", new Timestamp(currentTime));

                },
                "http://localhost:11910/oms",
                10000
        );

        monitoring.startMonitoring();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}