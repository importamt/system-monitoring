package com.eseict.monitoring.impl.transpoter;

import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;
import com.eseict.monitoring.transpoter.Transporter;

import java.util.List;

//It does not transport anything
public class VoidTransporter implements Transporter {
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
