package com.eseict.monitoring.impl.transpoter;

import com.eseict.monitoring.dto.CheckResult;
import com.eseict.monitoring.dto.Link;
import com.eseict.monitoring.dto.System;
import com.eseict.monitoring.transpoter.Transporter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RinoTransporter implements Transporter {

    private final String omsUrl;
    private final String systemKey;
    private final String secret;
    private final ObjectMapper objectMapper;

    public RinoTransporter(String omsUrl, String systemKey, String secret) {

        if (omsUrl.endsWith("/")) {
            this.omsUrl = omsUrl;
        } else {
            this.omsUrl = omsUrl + "/";
        }

        this.systemKey = systemKey;
        this.secret = secret;
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<System> retrieveSystems() {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(omsUrl + "api/monitoring/systems");
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            String responseText = EntityUtils.toString(response.getEntity());
            List<System> systems = objectMapper.readValue(responseText, new TypeReference<List<System>>() {
            });
            return systems;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Link> retrieveLinks() {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(omsUrl + "api/monitoring/links");
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            String responseText = EntityUtils.toString(response.getEntity());
            List<Link> links = objectMapper.readValue(responseText, new TypeReference<List<Link>>() {
            });
            return links;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void registerCheckResult(CheckResult checkResult) {
        List<CheckResult> checkResults = new ArrayList<>();
        checkResults.add(checkResult);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(omsUrl + "api/monitoring/checks");
        httpPost.setHeader("Content-Type", "application/json");
        StringEntity entity = null;
        try {
            entity = new StringEntity(objectMapper.writeValueAsString(checkResults), StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);
        try (CloseableHttpResponse response = client.execute(httpPost)) {

//            java.lang.System.out.println("ENTITY: " + objectMapper.writeValueAsString(checkResults));

            String responseText = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
