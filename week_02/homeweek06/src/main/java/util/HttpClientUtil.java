package util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author C5318521
 * @since 4/7/2021
 */
public class HttpClientUtil {

    public static String getResult(String url) throws IOException {

        String result = "";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {

            HttpGet request = new HttpGet(url);

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


    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801";
        String text = HttpClientUtil.getResult(url);
        System.out.println(text);
    }

}
