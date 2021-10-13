package com.eseict.monitoring.impl.transpoter;

import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

class RinoTransporterTest {

    RinoTransporter rinoTransporter;

    @BeforeEach
    void init() {
        rinoTransporter = new RinoTransporter("http://localhost:11910/oms", "oms", "1234");

    }

    @Test
    void test_retrieve_systems() {
        List<System> systems = rinoTransporter.retrieveSystems();
        Assertions.assertTrue(systems.size() > 0);
    }

    @Test
    void test_retrieve_links() {
        List<Link> links = rinoTransporter.retrieveLinks();
        Assertions.assertTrue(links.size() > 0);
    }

    @Test
    void test_register_check_result() {
        CheckResult sampleResult1 = new CheckResult("test1", "a", "b", "SUCCESS", new Timestamp(java.lang.System.currentTimeMillis()));
        CheckResult sampleResult2 = new CheckResult("test2", "a", "b", "SUCCESS", new Timestamp(java.lang.System.currentTimeMillis()));
        CheckResult sampleResult3 = new CheckResult("test3", "b", "a", "FAIL", new Timestamp(java.lang.System.currentTimeMillis()));

        rinoTransporter.registerCheckResult(sampleResult1);
        rinoTransporter.registerCheckResult(sampleResult2);
        rinoTransporter.registerCheckResult(sampleResult3);
    }
}