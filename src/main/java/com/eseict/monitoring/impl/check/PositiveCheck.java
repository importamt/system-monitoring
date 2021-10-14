package com.eseict.monitoring.impl.check;

import com.eseict.monitoring.check.Check;
import com.eseict.monitoring.dto.CheckResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;


public class PositiveCheck implements Check {
    private final Logger logger = LoggerFactory.getLogger(PositiveCheck.class);

    @Override
    public CheckResult check(String sourceId, String targetId) {
        long currentTime = System.currentTimeMillis();
        try {

            if (!sourceId.equals(targetId)) {
                //PID check: from === to
                logger.warn("from[{}] is not equals with to[{}]. nothing will happen. just warn you.", sourceId, targetId);
            }

            return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, "SUCCESS", new Timestamp(currentTime));
        } catch (UnsupportedOperationException e) {
            logger.error(e.getMessage(), e);
            return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, e.getMessage(), new Timestamp(currentTime));

        }
    }
}
