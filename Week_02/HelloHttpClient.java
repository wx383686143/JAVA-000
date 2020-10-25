import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

public class HelloHttpClient {

    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("http://localhost:8801");
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                InputStream in = entity.getContent();
                byte[] bytes = new byte[in.available()];
                in.read(bytes);
                System.out.println(new String(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
