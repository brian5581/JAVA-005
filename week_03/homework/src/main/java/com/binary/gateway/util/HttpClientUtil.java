package com.binary.gateway.util;


import io.netty.handler.codec.http.HttpHeaders;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author binary
 * @since 4/7/2021
 */
public class HttpClientUtil {

    public static String getResult(String url, HttpHeaders headers) throws IOException {

        String result = "test .....";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpGet request = new HttpGet(url);

            for (Map.Entry<String,String> entry:headers.entries()) {
                request.addHeader(entry.getKey(),entry.getValue());
            }
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (IOException ex) {
             ex.printStackTrace();
        } finally {
            httpClient.close();
        }

        return result;

    }


   /* public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String text = HttpClientUtil.getResult(url);
        System.out.println(text);
    }*/

}
