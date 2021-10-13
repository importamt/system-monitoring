package com.eseict.monitoring.check;

import com.eseict.monitoring.dto.CheckResult;

public interface Check {
    CheckResult check(String sourceId, String targetId);
}
