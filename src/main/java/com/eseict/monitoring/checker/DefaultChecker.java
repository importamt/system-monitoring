package com.eseict.monitoring.checker;

import com.eseict.monitoring.check.Check;
import com.eseict.monitoring.check.OtherCheck;
import com.eseict.monitoring.check.SelfCheck;
import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;
import com.eseict.monitoring.transpoter.Transporter;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultChecker implements Checker {


    @Override
    public void check() {
        //create transporter
        Transporter transporter = null;


        //get Systems and Links
        List<System> systems = transporter.retrieveSystems();
        List<Link> links = transporter.retrieveLinks();

        List<Check> checks = links.stream().map(link -> new OtherCheck()).collect(Collectors.toList());

        SelfCheck selfCheck = null;
        checks.add(selfCheck);

        systems.stream().forEach(system -> {
            Check check = null;
            CheckResult checkResult = check.check();
            transporter.registerCheckResult(checkResult);
        });

    }

}
