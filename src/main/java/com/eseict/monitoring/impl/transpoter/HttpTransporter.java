package com.eseict.monitoring.impl.transpoter;

import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;
import com.eseict.monitoring.transpoter.Transporter;

import java.util.List;

public class HttpTransporter implements Transporter {

    private String retrieveSystemsUrl;
    private String retrieveLinksUrl;
    private String registerCheckResultUrl;

    public HttpTransporter(String retrieveSystemsUrl, String retrieveLinksUrl, String registerCheckResultUrl) {
        this.retrieveSystemsUrl = retrieveSystemsUrl;
        this.retrieveLinksUrl = retrieveLinksUrl;
        this.registerCheckResultUrl = registerCheckResultUrl;
    }

    @Override
    public List<System> retrieveSystems() {
        //TODO: implement default retrieveSystems
        return null;
    }

    @Override
    public List<Link> retrieveLinks() {
        //TODO: implement default retrieveLinks
        return null;
    }

    @Override
    public void registerCheckResult(CheckResult checkResult) {
        //TODO: implement default registerCheckResult
    }

}
