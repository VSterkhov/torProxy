import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.*;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Vladislav Sterkhov on 13.08.2019
 */
public class TorHttp {
	static Integer HTTP_REQUEST_TIMEOUT = 5000;

	public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException, InterruptedException {
		HttpClient httpClient = prepareHttpClient();
		HttpGet httpGet = new HttpGet("https://check.torproject.org");
		HttpResponse response = httpClient.execute(httpGet);

		System.out.println(response.getStatusLine());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String line;
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
	}


	private static HttpClient prepareHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext context = HttpClientContext.create();
		context.setCookieStore(cookieStore);
		RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.DEFAULT)
				.setProxy(new HttpHost("127.0.0.1", 8123))
				.setSocketTimeout(HTTP_REQUEST_TIMEOUT)
				.setConnectTimeout(HTTP_REQUEST_TIMEOUT)
				.setConnectionRequestTimeout(HTTP_REQUEST_TIMEOUT)
				.build();

		SSLContext sslContext = SSLContexts.custom()
				.build();

		SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(
				sslContext,
				new String[]{"TLSv1", "TLSv1.2"},
				null,
				new DefaultHostnameVerifier());

		return HttpClientBuilder.create().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslFactory).build();
	}
}