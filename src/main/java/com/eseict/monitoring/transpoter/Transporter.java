package com.eseict.monitoring.transpoter;

import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;

import java.util.List;

public interface Transporter {
    List<System> retrieveSystems();

    List<Link> retrieveLinks();

    void registerCheckResult(CheckResult checkResult);
}
