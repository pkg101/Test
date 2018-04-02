package com.Metadata.test;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class RestLogin {
	public static JSONObject GetLoginObject() {
		final String username = "pkg101@arxxus.com";
		final String password = "salesforce@123UMYnuQmQutBnsS0oqVhhZc55";
		final String loginurl = "https://login.salesforce.com";
		final String grantservice = "/services/oauth2/token?grant_type=password";
		final String cleienid = "3MVG9d8..z.hDcPKcUSaWCUD_sh5HwG4HEkz0Phh5qziu3naoTWos3JoXP23U4Ge0.WDa.ttvdrt0LYLROffD";
		final String clientsecret = "8490169935719725099";
		


		HttpClient httpclient = HttpClientBuilder.create().build();
		String loginURL = loginurl + grantservice + "&client_id=" + cleienid + "&client_secret=" + clientsecret
				+ "&username=" + username + "&password=" + password;
		HttpPost httpPost = new HttpPost(loginURL);
       System.out.println(loginURL);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			System.out.println("respnse "+response.getStatusLine());
		} catch (ClientProtocolException cpException) {
			cpException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		final int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			System.out.println("Error authenticating to Force.com: " + statusCode);
			return null;
		}

		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}
		httpPost.releaseConnection();
		return jsonObject;
	}
}