import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class HelloHttpClient {

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        InputStream in = null;
        try {
            HttpGet httpGet = new HttpGet("http://localhost:8801");
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                in = entity.getContent();
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                System.out.println(new String(bytes));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != in) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
