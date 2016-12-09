import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Jo on 12/9/16.
 */
public class HttpClientTest {
	public static void main(String[] args){
		String url = "https://www.baidu.com";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			byte[] bytes = EntityUtils.toByteArray(entity);
			String result = new String(bytes,"utf8");
			System.out.println(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
