package com.example.wh.mytestmvplearn.utils;

import android.os.AsyncTask;
import android.util.Log;

public class HttpTask extends AsyncTask<String, Void, String> {

	private HttpUtils.OnHttpResultListener onHttpResultListener;

	public HttpTask(HttpUtils.OnHttpResultListener onHttpResultListener) {
		this.onHttpResultListener = onHttpResultListener;
	}

	@Override
	protected String doInBackground(String... params) {
		Log.d("123",params[0]);
		try {
			return HttpUtils.get(params[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
        Log.e("123","------error-------");
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
        Log.d("123","------result-------"+result);
		if (this.onHttpResultListener != null) {
			this.onHttpResultListener.onResult(result);
		}
	}

}
