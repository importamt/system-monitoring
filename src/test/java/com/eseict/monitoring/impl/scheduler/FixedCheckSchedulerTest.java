package com.eseict.monitoring.impl.scheduler;

import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.impl.checker.DefaultChecker;
import com.eseict.monitoring.impl.transpoter.VoidTransporter;
import com.eseict.monitoring.scheduler.CheckScheduler;
import com.eseict.monitoring.transpoter.Transporter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

class FixedCheckSchedulerTest {
    int delay = 0;
    List<Integer> checkTestList;
    CheckScheduler fixedCheckScheduler;
    Transporter transporter;
    Checker checker;

    @BeforeEach
    void init() {
        delay = 1000;

        checkTestList = new ArrayList<>();
        fixedCheckScheduler = new FixedCheckScheduler(delay);
        transporter = new VoidTransporter();
        checker = new DefaultChecker("TEST",
                (sourceId, targetId) -> {
                    long currentTime = System.currentTimeMillis();

                    checkTestList.add(0);
                    return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, "SUCCESS", new Timestamp(currentTime));
                }
                , transporter);
    }

    @Test
    public void test_scheduler_run_checker_with_fixed_delay() {
        fixedCheckScheduler.startSchedule(checker);

        try {
            Thread.sleep(delay * 3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //최소 2회 이상 돌았을 것
        Assertions.assertTrue(checkTestList.size() > 2);
    }

    @Test
    public void test_scheduler_stop() {
        fixedCheckScheduler.startSchedule(checker);

        try {
            Thread.sleep(delay * 3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fixedCheckScheduler.stopSchedule();

        try {
            Thread.sleep(delay * 3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertTrue(checkTestList.size() < 5);

    }

}