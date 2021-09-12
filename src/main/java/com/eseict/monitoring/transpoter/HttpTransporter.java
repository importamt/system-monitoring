package com.eseict.monitoring.transpoter;

import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;

import java.util.List;

public class HttpTransporter implements Transporter {
    @Override
    public List<System> retrieveSystems() {
        return null;
    }

    @Override
    public List<Link> retrieveLinks() {
        return null;
    }

    @Override
    public void registerCheckResult(CheckResult checkResult) {

    }

}
