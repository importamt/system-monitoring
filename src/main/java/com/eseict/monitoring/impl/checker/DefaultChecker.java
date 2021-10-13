package com.eseict.monitoring.impl.checker;

import com.eseict.monitoring.check.Check;
import com.eseict.monitoring.checker.Checker;
import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;
import com.eseict.monitoring.impl.check.HttpCheck;
import com.eseict.monitoring.transpoter.Transporter;

import java.util.List;

public class DefaultChecker implements Checker {
    private final String systemId;
    private final Check check;
    private final Transporter transporter;

    public DefaultChecker(String systemId, Check check, Transporter transporter) {
        this.systemId = systemId;
        this.check = check;
        this.transporter = transporter;
    }

    @Override
    public void check() {
        //get Systems and Links
        List<System> systems = transporter.retrieveSystems();
        List<Link> links = transporter.retrieveLinks();

        //Self check
        CheckResult selfCheckResult = check.check(systemId, systemId);
        transporter.registerCheckResult(selfCheckResult);

        if (links != null) {
            links.forEach(link -> {
                HttpCheck httpCheck = new HttpCheck(link.getLinkId());
                CheckResult checkResult = httpCheck.check(link.getSourceId(), link.getTargetId());
                transporter.registerCheckResult(checkResult);
            });
        }
    }

}
