package com.example.wh.mytestmvplearn.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {


	public static String get(String urlStr) {
		String result = null;
		try {
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setReadTimeout(5000);
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			if (connection.getResponseCode() == 200) {
				InputStream inStream = connection.getInputStream();
				result = new String(StreamTool.readInputStream(inStream),"gb2312");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public interface OnHttpResultListener {
		public void onResult(String result);
	}
}
