package com.eseict.monitoring.impl.scheduler;

import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.scheduler.CheckScheduler;

public class FixedCheckScheduler implements CheckScheduler {

    private int delay = 30000;

    private Thread workingThread;

    public FixedCheckScheduler() {
    }

    public FixedCheckScheduler(int delay) {
        this.delay = delay;
    }

    @Override
    public void startSchedule(Checker checker) {

        workingThread = new Thread(() -> {
            while (true) {
                try {

                    new Thread(checker::check).start();

                    Thread.sleep(delay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        workingThread.setName("system-monitoring-thread");
        workingThread.start();
    }

    @Override
    public void stopSchedule() {

        workingThread.interrupt();
    }
}
