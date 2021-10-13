package com.eseict.monitoring.impl.check;

import com.eseict.monitoring.check.Check;
import com.eseict.monitoring.dto.CheckResult;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;

public class HttpCheck implements Check {
    private final String uri;
    private final Logger logger = LoggerFactory.getLogger(HttpCheck.class);

    public HttpCheck(String uri) {
        this.uri = uri;
    }

    @Override
    public CheckResult check(String sourceId, String targetId) {
        long currentTime = System.currentTimeMillis();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            String responseText = EntityUtils.toString(response.getEntity());
            int responseStatusCode = response.getStatusLine().getStatusCode();

            if (responseStatusCode / 100 == 2) {
                return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, "SUCCESS", new Timestamp(currentTime));
            } else {
                return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, "FAIL", new Timestamp(currentTime));
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new CheckResult(sourceId + "_" + targetId + "_" + currentTime, sourceId, targetId, e.getMessage(), new Timestamp(currentTime));
        }
    }
}
