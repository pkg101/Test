package com.Metadata.test;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;



public class DataWarehouse {
	public static JSONArray getCustomObjectList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getCustomObjects(startdate, enddate);
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomObjectList : " + e);
		}
		return jsonArray;
	}

	public static JSONArray getCustomFieldList(JSONObject loginObject, String startdate, String enddate) {
		// List<String> customObjectList = new ArrayList<String>();
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getCustomFields(startdate, enddate);
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				jsonArray = jsonObject.getJSONArray("records");
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomFieldList : " + e);
		}
		return jsonArray;
	}
	public static JSONArray getApexTriggerList(JSONObject loginObject, String startdate, String enddate) {
		JSONArray jsonArray = null;
		String ObjectRestURL = ToolingQueryList.getApexTriggers(startdate, enddate);

		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);

		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);

		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);

				jsonArray = jsonObject.getJSONArray("records");
				/*
				 * for(int i=0;i<jsonArray.length();i++) {
				 * metadtalist.add(jsonArray.getJSONObject(i).getString("Id")); }
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;

	}

	public static String getCustomObjectName(JSONObject loginObject, String ObjectID) {
		String objname = null;
		String ObjectRestURL = ToolingQueryList.getObjectNameQuery(ObjectID);
		HttpClient httpClient = HttpClientBuilder.create().build();

		String instanceURL = loginObject.getString("instance_url");
		String AccessToken = loginObject.getString("access_token");

		Header oauthHeader = new BasicHeader("Authorization", "OAuth " + AccessToken);
		String uri = instanceURL + RestResourceURL.getToolingQueryURL(ObjectRestURL);
		HttpResponse response = null;
		HttpGet httpget = new HttpGet(uri);
		httpget.addHeader(oauthHeader);
		try {
			response = httpClient.execute(httpget);
			if (response.getStatusLine().getStatusCode() == 200) {
				String Result = EntityUtils.toString(response.getEntity());
				JSONObject jsonObject = new JSONObject(Result);
				JSONArray jsonArray = jsonObject.getJSONArray("records");
				if (jsonArray.length() > 0)
					objname = jsonArray.getJSONObject(0).getString("DeveloperName");
				/*
				 * for (int i = 0; i < jsonArray.length(); i++) {
				 * System.out.println(jsonArray.getJSONObject(i).getString("DeveloperName")); }
				 */
			}
		} catch (Exception e) {
			System.out.println("Error in getCustomObjectName : " + e);
		}
		return objname;
	}
}